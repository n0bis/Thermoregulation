import "./App.css";
import { ResponsiveLine } from "@nivo/line";
import { useEffect, useState } from "react";
import mqtt from "precompiled-mqtt";

// mosquitto test broker
const URL = "ws://localhost:9001";

const client = mqtt.connect(URL);

function App() {
  const [data, setData] = useState([]);
  const [isLoading, setLoading] = useState(true);

  useEffect(() => {
    getData();
    //getAlerts();
    var intervalId = window.setInterval(function () {
      getData();
    }, 5000);
  }, []);

  function getData() {
    setLoading(true);
    fetch("http://localhost:4000", {
      body: '{"query":"query ExampleQuery {sensors {id,temperature { value, tracked_at}}}"}',
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
    })
      .then((response) => response.json())
      .then((response) => {
        console.log(response.data);
        const { data } = response;
        const { temperatures } = data;

        const fixedData = [];

        data.sensors.forEach((item) => {
          fixedData.push({
            id: `Sensor ${item.id}`,
            data: dataConverter(item.temperature),
          });
        });
        console.log(fixedData);

        setData(fixedData);
        setLoading(false);

        console.log(temperatures);
      });
  }

  useEffect(() => {
    //console.log(data);
  }, [data]);

  return (
    <div className="page">
      <div className="sidebar">
        <ul>
          <li className="active">
            <a href="#">Dashboard</a>
          </li>
          <li>
            <a href="#">Rules manager</a>
          </li>
        </ul>
      </div>
      <div className="page-content">
        <div className="header">
          <div className="container">
            <h2>Welcome back</h2>
            <h4>Dashboard</h4>
          </div>
        </div>
        <div className="content">
          <div className="container">
            <div className="chart">
              <div className="red-line" />
              {!isLoading && data.length !== 0 && (
                <MyResponsiveLine data={data} />
              )}
              <div className="blue-line" />
            </div>
            <AlertList />
          </div>
        </div>
      </div>
    </div>
  );
}

function dataConverter(data) {
  var convertedData = [];
  console.log(data);

  data.forEach((element) => {
    var date = new Date(element.tracked_at);
    console.log(date.toDateString());
    convertedData.push({
      x: element.tracked_at,
      y: element.value,
    });
  });

  return convertedData;
}

const AlertList = () => {
  const [alerts, setAlerts] = useState([]);

  useEffect(() => {
    getAlerts();
    //setAlerts([{ message: "LEDblink: blue", timestamp: "shiusasa" }]);
  }, []);

  useEffect(() => {
    console.log(alerts);
  }, [alerts]);

  function getAlerts() {
    try {
      client.on("connect", () => {
        console.log("CONNECTED to broker");
      });

      client.on("connect", function () {
        client.subscribe("rules/alert", function (err) {
          if (!err) {
            console.log("subscribed!");
          }
        });
      });

      client.on("message", function (topic, message) {
        // message is Buffer
        var alertsArray = alerts;
        const currentDate = new Date();
        const timestamp = currentDate.getTime();
        const msg = new TextDecoder().decode(message);
        console.log(msg);
        alertsArray.push({
          msg,
          currentDate,
        });
        setAlerts(alertsArray);
      });
    } catch (e) {
      console.log(e);
    }
  }

  return (
    <div className="alert-box">
      <h4>Incomming alerts</h4>
      {alerts.length !== 0 ? (
        <ul>
          {alerts.map((item) => {
            const response = item.msg.split(" ");
            const color = response[1];
            if (response[0] !== "undefined") {
              if (color === "blue") {
                return (
                  <li className={`alert-info blue`}>
                    <p>
                      It is too cold - {item.currentDate.getHours()}{" "}
                      {item.currentDate.getMinutes()}
                    </p>
                  </li>
                );
              } else if (color === "red") {
                return (
                  <li className={`alert-info red`}>
                    <p>
                      It is too hot {item.currentDate.getHours()}{" "}
                      {item.currentDate.getMinutes()}
                    </p>
                  </li>
                );
              }
            }
          })}
        </ul>
      ) : (
        <div className="alert-info">No alerts found.</div>
      )}
    </div>
  );
};

const MyResponsiveLine = ({ data /* see data tab */ }) => (
  <ResponsiveLine
    data={data}
    margin={{ top: 50, right: 110, bottom: 50, left: 60 }}
    xScale={{ type: "point" }}
    yScale={{
      type: "linear",
      min: "-40",
      max: "40",
      stacked: false,
      reverse: false,
    }}
    minValue={-40}
    maxValue={30}
    yFormat=" >-.2f"
    curve="cardinal"
    axisTop={null}
    axisRight={null}
    axisBottom={{
      orient: "bottom",
      tickSize: 5,
      tickPadding: 5,
      tickRotation: 0,
      legend: "Time",
      tickValues: 3,
      legendOffset: 36,
      legendPosition: "middle",
    }}
    axisLeft={{
      orient: "left",
      tickSize: 5,
      tickPadding: 5,
      tickRotation: 0,
      legend: "count",
      legendOffset: -40,
      legendPosition: "middle",
      maxValue: 301,
    }}
    pointSize={10}
    pointColor={{ theme: "background" }}
    pointBorderWidth={2}
    pointBorderColor={{ from: "serieColor" }}
    pointLabelYOffset={-12}
    useMesh={true}
    legends={[
      {
        anchor: "bottom-right",
        direction: "column",
        justify: false,
        translateX: 100,
        translateY: 0,
        itemsSpacing: 0,
        itemDirection: "left-to-right",
        itemWidth: 80,
        itemHeight: 20,
        itemOpacity: 0.75,
        symbolSize: 12,
        symbolShape: "circle",
        symbolBorderColor: "rgba(0, 0, 0, .5)",
        effects: [
          {
            on: "hover",
            style: {
              itemBackground: "rgba(0, 0, 0, .03)",
              itemOpacity: 1,
            },
          },
        ],
      },
    ]}
  />
);

export default App;

/*
curl --request POST \
    --header 'content-type: application/json' \
    --url http://localhost:4000 \
    --data '{"query":"query ExampleQuery {\n  temperatures {\n    value\n    tracked_at\n  }\n  trucks {\n    name\n  }\n}"}'
*/

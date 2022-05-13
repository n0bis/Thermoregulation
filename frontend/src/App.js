import "./App.css";
import { ResponsiveLine } from "@nivo/line";
import { useEffect, useState } from "react";

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    getData();
    var intervalId = window.setInterval(function () {
      getData();
    }, 5000);
  }, []);

  function getData() {
    fetch("http://localhost:4000", {
      body: '{"query":"query ExampleQuery {temperatures {value tracked_at} trucks {name}}"}',
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
    })
      .then((response) => response.json())
      .then((response) => {
        console.log(response.data.temperatures);
        const { data } = response;
        const { temperatures } = data;

        setData([
          {
            id: "Temperature",
            color: "hsl(84, 70%, 50%)",
            data: dataConverter(temperatures),
          },
        ]);

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
            <a href="#">Dashbsoard</a>
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
              {data.length !== 0 && <MyResponsiveLine data={data} />}
            </div>
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
    convertedData.push({
      x: element.tracked_at,
      y: element.value,
    });
  });

  return convertedData;
}

const MyResponsiveLine = ({ data /* see data tab */ }) => (
  <ResponsiveLine
    data={data}
    margin={{ top: 50, right: 110, bottom: 50, left: 60 }}
    xScale={{ type: "point" }}
    yScale={{
      type: "linear",
      min: "auto",
      max: "auto",
      stacked: true,
      reverse: false,
    }}
    minValue={-40}
    maxValue={40}
    yFormat=" >-.2f"
    curve="cardinal"
    axisTop={null}
    axisRight={null}
    axisBottom={{
      orient: "bottom",
      tickSize: 5,
      tickPadding: 5,
      tickRotation: 0,
      legend: "transportation",
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

const staticData = [
  {
    id: "Temperature",
    color: "hsl(84, 70%, 50%)",
    data: [
      {
        x: "00",
        y: 278,
      },
      {
        x: "01",
        y: 80,
      },
      {
        x: "03",
        y: 285,
      },
      {
        x: "04",
        y: 259,
      },
      {
        x: "05",
        y: 210,
      },
      {
        x: "06",
        y: 23,
      },
      {
        x: "07",
        y: 27,
      },
      {
        x: "08",
        y: 271,
      },
      {
        x: "09",
        y: 298,
      },
      {
        x: "10",
        y: 233,
      },
      {
        x: "11",
        y: 267,
      },
      {
        x: "12",
        y: 90,
      },
      {
        x: "13",
        y: 23,
      },
      {
        x: "14",
        y: 27,
      },
      {
        x: "15",
        y: 271,
      },
      {
        x: "16",
        y: 298,
      },
      {
        x: "17",
        y: 233,
      },
      {
        x: "18",
        y: 267,
      },
      {
        x: "19",
        y: 90,
      },
      {
        x: "20",
        y: 23,
      },
      {
        x: "21",
        y: 27,
      },
      {
        x: "22",
        y: 271,
      },
      {
        x: "23",
        y: 298,
      },
    ],
  },
];

export default App;

/*
curl --request POST \
    --header 'content-type: application/json' \
    --url http://localhost:4000 \
    --data '{"query":"query ExampleQuery {\n  temperatures {\n    value\n    tracked_at\n  }\n  trucks {\n    name\n  }\n}"}'
*/

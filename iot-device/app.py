import paho.mqtt.client as mqtt 
from random import randrange, uniform
from time import sleep

mqttBroker = "tcp://127.0.0.1:1883" 

client = mqtt.Client("Temperature_Inside")
client.connect(mqttBroker) 

while True:
    randNumber = randrange(0, 21.0)
    event = {"temperatureID": "sensor-iot-temp", "value": randNumber, "truckID": 1}
    client.publish("TEMPERATURE", event)
    print(f"Just published {event} to topic TEMPERATURE")
    sleep(5)
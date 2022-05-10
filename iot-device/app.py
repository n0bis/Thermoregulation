import paho.mqtt.client as mqtt 
from random import randrange, uniform
from time import sleep, time

mqttBroker = "tcp://mqtt:1883" 

client = mqtt.Client("Temperature_Inside")
client.connect(mqttBroker) 

while True:
    randNumber = randrange(0, 21.0)
    event = {"value": randNumber, "timestamp": int(time())}
    client.publish("TEMPERATURE", event)
    print(f"Just published {event} to topic TEMPERATURE")
    sleep(5)
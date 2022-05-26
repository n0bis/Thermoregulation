import paho.mqtt.client as mqtt 
from random import randrange, uniform
from time import sleep, time
import json

client = mqtt.Client("Temperature_Inside")
client.connect("localhost", 1883, 60) 

while True:
    for id in range(1, 5):
        randNumber = randrange(0, 30.0)
        event = {
            "id": id,
            "value": randNumber, 
            "timestamp": int(time())}
        client.publish("TEMPERATURE", json.dumps(event))
        print(f'Just published {json.dumps(event)} to topic TEMPERATURE')
    sleep(5)


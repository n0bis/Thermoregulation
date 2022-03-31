import paho.mqtt.client as mqtt 
from random import randrange, uniform
from time import sleep

mqttBroker ="localhost:1883" 

client = mqtt.Client("Temperature_Inside")
client.connect(mqttBroker) 

while True:
    randNumber = randrange(0, 21.0)
    event = {"customer": {"customerID": "DOE", "contactName": "John Doe", "companyName": "Acme Corporation"}, "order": {"orderID": randNumber, "shipCity": "DoeCity", "shipAddress": "Via Doe 7"}, "product": {"productID": "77", "quantity": randNumber, "unitPrice": "10.0"}}
    client.publish("TEMPERATURE", event)
    print(f"Just published {event} to topic TEMPERATURE")
    sleep(5)
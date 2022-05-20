from neo4j import GraphDatabase
from confluent_kafka import Consumer
import paho.mqtt.client as mqtt 
import os
import json

uri = "neo4j://localhost:7687"
driver = GraphDatabase.driver(uri, auth=(os.getenv('NEO4J_USER', 'neo4j'), os.getenv('NEO4J_PASSWORD', '2cool4school')))
session = driver.session()
def on_connect(client, userdata, flags, rc):
    print(f"Connected with result code {rc}")
client = mqtt.Client("rules_engine")
client.on_connect = on_connect
client.connect("localhost", 1884, 60)
client.loop_start()

consumer = Consumer({
	'group.id': 'foo',
    'bootstrap.servers': 'localhost',
    'auto.offset.reset': 'latest'
})
consumer.subscribe(['temperature'])

while True:
	msg = consumer.poll(1.0)

	if msg is None:
		continue
	if msg.error():
		print("Consumer error: {}".format(msg.error()))
		continue
	
	try:
		print('Received message: {}'.format(msg.value().decode('utf-8')))
		event = json.loads(msg.value().decode('utf-8'))
		
		if event["id"] == 1:
			if 15 < event["value"]:
				client.publish("rules/alert", json.dumps({"sensor": 1, "LEDblink": "red"}))
				print("Sensor 1 Too hot! - publish to mqtt")
				continue
		if event["id"] == 2:
			if 15 < event["value"]:
				client.publish("rules/alert", json.dumps({"sensor": 2, "LEDblink": "red"}))
				print("Sensor 2 Too hot! - publish to mqtt")
				continue
		if event["id"] == 3:
			if 15 < event["value"]:
				client.publish("rules/alert", json.dumps({"sensor": 3, "LEDblink": "red"}))
				print("Sensor 3 Too hot! - publish to mqtt")
				continue
		if event["id"] == 1:
			if 5 > event["value"]:
				client.publish("rules/alert", json.dumps({"sensor": 1, "LEDblink": "blue"}))
				print("Sensor 1 Too cold! - publish to mqtt")
				continue
		if event["id"] == 2:
			if 10 > event["value"]:
				client.publish("rules/alert", json.dumps({"sensor": 2, "LEDblink": "red"}))
				print("Sensor 2 Too cold! - publish to mqtt")
				continue

		print("No rule broken move along - publish to mqtt")
		client.publish("rules/alert", "")
	except Exception as err:
		print(err)
		continue

c.close()


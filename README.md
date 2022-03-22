# Thermoregulation
 
Have to seed manually: ```/neo4j/seeddata_seed.cql ```
http://localhost:7474/browser/

test to populate new data: 
```mosquitto_pub -t 'test/topic' -h localhost -p 1883 -f northwind-events.txt```

neo4j browser: ```MATCH path=(customer:Customer)-[:PURCHASED]->(order:Order)-[:ORDERS]->(product:Product) WHERE customer.customerID = 'DOE' RETURN path```
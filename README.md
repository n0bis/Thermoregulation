# Thermoregulation
 
neo4j will now automatically populate the db

test to populate new data (navigate to iot-device folder): 
```mosquitto_pub -t 'test/topic' -h localhost -p 1883 -f temperature-events.txt```

watch in neo4j browser

# Dashboards - Backend

## Neo4j
```http://localhost:7474/browser/```

## GraphQL
```http://localhost:4000```

## KOWL
```http://localhost:8088```
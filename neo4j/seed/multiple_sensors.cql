CREATE (truck:Truck {name: 'Betty'})
CREATE (sensor1:Sensor {id: 1, description: 'sensor 1 in cold storage'})
CREATE (sensor2:Sensor {id: 2, description: 'sensor 2 in cold storage'})
CREATE (sensor3:Sensor {id: 3, description: 'sensor 3 in cold storage'})
CREATE (sensor4:Sensor {id: 4, description: 'sensor 4 in cold storage'})
CREATE
(sensor1)-[:BELONGS_TO]->(truck),
(sensor2)-[:BELONGS_TO]->(truck),
(sensor3)-[:BELONGS_TO]->(truck),
(sensor4)-[:BELONGS_TO]->(truck)
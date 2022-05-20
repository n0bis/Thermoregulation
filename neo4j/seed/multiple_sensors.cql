CREATE (truck:Truck {name: 'Betty'})
CREATE (sensor1:Sensor {description: 'sensor 1'})
CREATE (sensor2:Sensor {description: 'sensor 2'})
CREATE (sensor3:Sensor {description: 'sensor 3'})
CREATE (sensor4:Sensor {description: 'sensor 4'})
CREATE
(sensor1)-[:BELONGS_TO]->(truck),
(sensor2)-[:BELONGS_TO]->(truck),
(sensor3)-[:BELONGS_TO]->(truck),
(sensor4)-[:BELONGS_TO]->(truck)
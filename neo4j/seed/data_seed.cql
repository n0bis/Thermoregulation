CREATE (truck:Truck {name: 'Betty'})
CREATE (temperature1:Temperature {value: 16.1, tracked_at: localdatetime()})
CREATE (temperature2:Temperature {value: 16.3, tracked_at: localdatetime()})
CREATE (temperature3:Temperature {value: 15.5, tracked_at: localdatetime()})
CREATE (temperature4:Temperature {value: 16.4, tracked_at: localdatetime()})
CREATE (rule:Rule {nameSpace: '', conditions: '', actions: ''})
CREATE
(temperature1)-[:TRACKED_BY]->(truck),
(temperature2)-[:TRACKED_BY]->(truck),
(temperature3)-[:TRACKED_BY]->(truck),
(temperature4)-[:TRACKED_BY]->(truck)
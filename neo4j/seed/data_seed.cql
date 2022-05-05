LOAD CSV WITH HEADERS FROM 'file:///trucks.csv' AS row
CREATE (n:Truck)
SET n = row;

CREATE INDEX ON :Truck(truckID);

MATCH (t:Truck),(c:Company)
WHERE t.companyID = c.companyID
CREATE (t)-[:OWNED_BY]->(c);

LOAD CSV WITH HEADERS FROM 'file:///temperatures.csv' AS row
CREATE (n:Temperature)
SET n = row,
n.value = toFloat(row.value);

CREATE INDEX ON :Temperature(temperatureID);

MATCH (temp:Temperature),(t:Truck)
WHERE temp.truckID = t.truckID
CREATE (temp)-[:TRACKED]->(t);

LOAD CSV WITH HEADERS FROM 'file:///rules.csv' AS row
CREATE (n:Rule)
SET n = row;

CREATE INDEX ON :Rule(ruleID);

MATCH (r:Rule),(c:Company)
WHERE r.companyID = c.companyID
CREATE (r)-[:BELONGS_TO]->(c);
LOAD CSV WITH HEADERS FROM 'file:///companies.csv' AS row
CREATE (n:Company)
SET n = row;

CREATE INDEX ON :Company(companyID);

LOAD CSV WITH HEADERS FROM 'file:///categories.csv' AS row
CREATE (n:Category)
SET n = row;

CREATE INDEX ON :Category(categoryID);

MATCH (category:Category),(company:Company)
WHERE category.categoryID = company.companyID
CREATE (category)-[:OWNED_BY]->(company);

LOAD CSV WITH HEADERS FROM 'file:///products.csv' AS row
CREATE (n:Product)
SET n = row;

MATCH (p:Product),(c:Category)
WHERE p.categoryID = c.categoryID
CREATE (p)-[:PART_OF]->(c);

MATCH (p:Product),(c:Company)
WHERE p.companyID = c.companyID
CREATE (p)-[:OWNED_BY]->(c);

CREATE INDEX ON :Product(productID);

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

LOAD CSV WITH HEADERS FROM 'file:///packages.csv' AS row
CREATE (n:Package)
SET n = row;

CREATE INDEX ON :Package(packageID);

MATCH (p:Package),(t:Truck)
WHERE p.truckID = t.truckID
CREATE (temp)-[:BELONGS_TO]->(t);

MATCH (p:Package),(c:Company)
WHERE p.companyID = c.companyID
CREATE (p)-[:OWNED_BY]->(c);
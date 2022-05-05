const { gql, ApolloServer } = require("apollo-server");
const { Neo4jGraphQL } = require("@neo4j/graphql");
const neo4j = require("neo4j-driver");
require("dotenv").config();

const typeDefs = gql`
  type Company {
    companyID: Int
    companyName: Int
    categories: [Category!]! @relationship(type: "OWNED_BY", direction: IN)
    products: [Product!]! @relationship(type: "OWNED_BY", direction: IN)
    trucks: [Truck!]! @relationship(type: "OWNED_BY", direction: IN)
    rules: [Rule!]! @relationship(type: "BELONGS_TO", direction: IN)
    packages: [Package!]! @relationship(type: "OWNED_BY", direction: IN)
  }

  type Category {
    categoryID: Int
    categoryName: String
    companyID: Int
    company: [Company!]! @relationship(type: "OWNED_BY", direction: OUT)
  }

  type Product {
    productID: Int
    productName: String
    categoryID: Int
    companyID: Int
    category: [Category!]! @relationship(type: "PART_OF", direction: OUT)
    company: [Company!]! @relationship(type: "OWNED_BY", direction: OUT)
  }

  type Truck {
    truckID: Int
    truckName: String
    CompanyID: Int
    company: [Company!]! @relationship(type: "OWNED_BY", direction: OUT)
  }

  type Temperature {
    temperatureID: Int
    value: Float
    time: DateTime
    truckID: Int
    truck: [Truck!]! @relationship(type: "TRACKED", direction: OUT)
  }

  type Rule {
    ruleID: Int
    nameSpace: String
    conditions: String
    actions: String
    companyID: Int
    company: [Company!]! @relationship(type: "BELONGS_TO", direction: OUT)
  }

  type Package {
    packageID: Int
    companyID: Int
    shippedDate: DateTime
    deliveredDate: DateTime
    truckID: Int
    truck: [Truck!]! @relationship(type: "BELONGS_TO", direction: OUT)
    company: [Company!]! @relationship(type: "OWNED_BY", direction: OUT)
  }
`;

const driver = neo4j.driver(
  process.env.NEO4J_URI,
  neo4j.auth.basic(process.env.NEO4J_USER, process.env.NEO4J_PASSWORD)
);

const neoSchema = new Neo4jGraphQL({ typeDefs, driver });

neoSchema.getSchema().then((schema) => {
    const server = new ApolloServer({
        schema: schema
    });

    server.listen().then(({ url }) => {
        console.log(`GraphQL server ready on ${url}`);
    });
});
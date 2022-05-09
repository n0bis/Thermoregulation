const { gql, ApolloServer } = require("apollo-server");
const { Neo4jGraphQL } = require("@neo4j/graphql");
const neo4j = require("neo4j-driver");
require("dotenv").config();

const typeDefs = gql`
  type Truck {
    id: ID! @id
    name: String
  }

  type Temperature {
    id: ID! @id
    value: Float
    tracked_at: Int
    truck: [Truck!]! @relationship(type: "TRACKED_BY", direction: OUT)
  }

  type Rule {
    id: ID! @id
    nameSpace: String
    conditions: String
    actions: String
  }
`;

const driver = neo4j.driver(
  `neo4j://${process.env.NEO4J_URI}`,
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
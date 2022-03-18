#!/usr/bin/env python3
from neo4j import GraphDatabase

HOSTNAME = "neo4j"
PORT     = 7687
USERNAME = ""
PASSWORD = ""

###############################################################################
################################################################## Helpers ####

def pprint (data):
  for ix, record in enumerate(data):
    vs = record.values()
    print(ix, vs)

###############################################################################
############################################################### initialize ####

auth = (USERNAME, PASSWORD)
driver = GraphDatabase.driver("bolt://%s:%d" % (HOSTNAME, PORT), auth=None)
session = driver.session()

###############################################################################
################################################################## queries ####

q3 = \
'''
MATCH
  (person:Person)
RETURN person.firstName, person.lastName
'''
print('List all persons with gender:')
with driver.session() as session:
  data = session.run(q3)
  pprint(data)
print('')

###############################################################################
################################################################# finalize ####

driver.close()


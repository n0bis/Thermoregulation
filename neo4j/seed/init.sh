#!/bin/bash
# chmod +x init.sh
log_info() {
  printf '%s %s\n' "$(date -u +"%Y-%m-%d %H:%M:%S:%3N%z") INFO  Wrapper: $1"
  return
}

set -m

/docker-entrypoint.sh neo4j &

log_info "Waiting until neo4j stats at :7474 ..."
wget --quiet --tries=10 --waitretry=30 -O /dev/null bolt://localhost:7474
wget --quiet --tries=10 --waitretry=30 -O /dev/null bolt://localhost:7687
if [ -d "/cyphers" ]; then
  log_info  "Deleting all relations"
  cypher-shell --format plain "MATCH (n) DETACH DELETE n"

  log_info  "Wrapper: Loading cyphers from '/cyphers'"
  for cipherFile in /cyphers/*.cql; do
      log_info "Running cypher ${cipherFile}"
      contents=$(cat ${cipherFile})
      cypher-shell -a bolt://localhost:7687 "${contents}"
  done
  log_info  "Finished loading all cyphers from '/cyphers'"
fi

TOTAL_CHANGES=$(cypher-shell --format plain "MATCH (n) RETURN count(n) AS count")
# https://stackoverflow.com/questions/15520339/how-to-remove-carriage-return-and-newline-from-a-variable-in-shell-script/15520508#15520508
log_info "Wrapper: Changes $(echo ${TOTAL_CHANGES} | sed -e 's/[\r\n]//g')"

# now we bring the primary process back into the foreground
# and leave it there
fg %1
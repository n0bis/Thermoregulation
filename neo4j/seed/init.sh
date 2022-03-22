#!/bin/bash
# chmod +x init.sh
log_info() {
  printf '%s %s\n' "$(date -u +"%Y-%m-%d %H:%M:%S:%3N%z") INFO  Wrapper: $1"
  return
}
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
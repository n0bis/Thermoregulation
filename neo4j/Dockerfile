FROM neo4j:4.4.4

COPY seed/init.sh wrapper.sh
COPY seed/*.csv import/

VOLUME /cyphers

ENTRYPOINT ["./wrapper.sh"]
FROM neo4j:4.4.4

COPY seed/init.sh wrapper.sh

VOLUME /cyphers

ENTRYPOINT ["./wrapper.sh"]
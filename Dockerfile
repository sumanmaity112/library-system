FROM postgres:9.6

RUN mkdir -p /docker-entrypoint-initdb.d

COPY scripts/database/* /docker-entrypoint-initdb.d/

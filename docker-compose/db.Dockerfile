FROM postgres:11

COPY ./db/1-is2-postgres-create.sql /docker-entrypoint-initdb.d/
COPY ./db/2-is2-init.sql /docker-entrypoint-initdb.d/
COPY ./db/3-is2-relais.sql /docker-entrypoint-initdb.d/

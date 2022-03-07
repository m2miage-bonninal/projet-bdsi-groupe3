#!/bin/sh

docker run \
    --name mariadb \
    --rm --detach \
    --env MARIADB_DATABASE=ave \
    --env MARIADB_USER=admin \
    --env MARIADB_PASSWORD=changeme \
    --env MARIADB_ROOT_PASSWORD=changeme  \
    -p 3306:3306 \
    mariadb

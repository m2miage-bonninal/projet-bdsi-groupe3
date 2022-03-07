#!/bin/sh

docker run \
    --name myadmin \
    --rm --detach \
    --link mariadb:db \
    -p 8080:80 \
    phpmyadmin
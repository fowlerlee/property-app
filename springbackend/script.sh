#!/usr/bin/env bash

# get into container and make a DB named demodb
docker run -d -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=password postgres

docker exec -it postgres bash

psql -U postgres

CREATE DATABASE demodb;



# test the distributed lock
ab -n 10 -c 5 http://localhost:8080/perform/lock-key



#!/bin/bash

docker-compose stop  &>/dev/null
docker-compose rm -f &>/dev/null
docker-compose build &>/dev/null
docker-compose up
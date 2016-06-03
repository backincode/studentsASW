#!/bin/bash
################## BUILDING PORTALE ################


gradle -p portale/ war
mv portale/build/libs/portale.war utils/portale.war

################## BUILD DOCKER DATABASE PORTALE ################

docker build -t studentdb --file=./docker/postgres/Dockerfile .

################## BUILD DOCKER SERVER PORTALE ################

docker build -t studentserver --file=./docker/tomcat/Dockerfile .

################## STARTUP ################

docker run --name portaledb -p 5432:5432 studentdb 2>/dev/null &
sleep 1
docker run --name portale -p 8080:8080 --link portaledb:db studentserver 2>/dev/null &

################## END ################

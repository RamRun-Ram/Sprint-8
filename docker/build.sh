#!/usr/bin/env bash

mvn install
docker build -t spring-docker-image .
docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 80:8080 --name spring-docker spring-docker-image
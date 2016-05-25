#!/usr/bin/env bash
cd producer
mvn clean package -P con

cd ../consumer
mvn clean package -P con

cd ../web
mvn clean package -P con

cd ../persistor
mvn clean package -P con

cd ..
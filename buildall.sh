#!/usr/bin/env bash
cd producer
mvn clean install -P con

cd ../consumer
mvn clean install -P con

cd ../web
mvn clean install -P con

cd ../persistor
mvn clean install -P con

cd ..
mvn clean install -P con
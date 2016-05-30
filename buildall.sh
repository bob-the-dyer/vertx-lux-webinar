#!/usr/bin/env bash
cd producer
mvn clean install -P con
cd ..

cd consumer
mvn clean install -P con
cd ..

cd web
mvn clean install -P con
cd ..

cd persistor
mvn clean install -P con
cd ..

cd ha
mvn clean install -P con
cd ..
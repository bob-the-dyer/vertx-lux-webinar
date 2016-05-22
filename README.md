Vert'x: Beauty and The Beast

The goal is to show how to use vert.x to develop, deploy and maintain micro-services.
To build project use: mvn clean package

Verticals are deployed as a fat jar in both "native" and embedded modes inside Docker containers. 
To build project, deploy and run microservices in Docker use indocker profile: mvn clean package -P indocker 

Hints for docker interaction:
 - To initialize docker environment run in terminal: eval "$(docker-machine env default)"
 - Docker bridge network is 172.17.0.X
 - To kill all containers in once use: docker kill $(docker ps -q)
 - To remove all containters in once use:
    1. docker stop $(docker ps -a -q)
    2. docker rm $(docker ps -a -q)
 - To remove all none images use: docker rmi $(docker images | grep "^<none>" | awk "{print $3}")
 - To run mongodb in docker container use: docker run -it --rm --name lux-mongo -p 27017:27017 mongo
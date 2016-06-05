    Vert'x: Beauty and The Beast

The goal is to show how to use vert.x to develop, deploy and maintain micro-services.
To build project use: mvn clean package

Verticals are deployed as a fat jar in both "native"(via vertx launcher) and embedded (inside spring boot app) 
modes inside Docker containers. 
To build project, deploy and run microservices in Docker use: mvn clean package -P con,run
 - con profile - creates Docker image
 - run profile - run Docker image 
To build all containers at once use: sh buildall.sh
To run all containers at once use: docker-compose up 
To run hanode from maven: mvn package -P con,run -Dvertx.options="-ha" -Dstart.verticle="producer.groovy"

 
    Plan for webex
 - Who am I
 - Intro: why vert.x
 - Producer: exploring groovy code, maven build, Dockerfile, deployment to docker
 - Consumer: exploring ruby code, maven build, Dockerfile, deployment to docker
 - Start mongo in docker
 - Persistor: exploring java code, maven build, Dockerfile, deployment to docker
    - vert.x shell interactions
    - JMX, jsonsole, visualVM (a lots of options should be added) - 192.168.99.100:1898 for boot2docker, localhost:1898 for linux
 - web: exploring js client and server code, maven build, Dockerfile, deployment to docker, test in browser
 - vert.x console, ha demo
 - split brain demo: linux vm launch, blockade
 - Resume 
 
 
    Bonus topics
- https://www.techempower.com/benchmarks/
- comparing with akka
- vert.x internals: event loop, worker pool, shared data, nio
        

    Negative things to mention
 - hazelcast by default has timeouts and delays that should be configured for each particular case.    
 - rmi host on boot2docker needs to be specified (-Djava.rmi.server.hostname='192.168.99.100'), not on linux
 - ports will not automatically be opened while migrating from container to container, i.e. migration of web is useless,
 use super ha node with all ports needed
 - hanode - StarterVerticle is replicated in ha node instead of target verticle in vertx.deploy() -> solved with Launcher
 - hanode - looks like quorum is set by default with -ha
 - on split brain - no quorum -> undeploy -> trying to connect replica node -> adding to black list -> removing node 
    -> not reconnecting... totally depends on how cluster management works in hazelcast and how 
    hazalcast's timeouts and delays are configured. I doubt that HA based on hazelcast can be reliable and predictive
    especially on network partitioning. Need to try JGroups cluster management but as of now it's in technical review.   
 

    Hints for docker interaction:
 - To initialize docker environment run in terminal: eval "$(docker-machine env default)"
 - Docker bridge network is 172.17.0.X
 - Docker machine address is 192.168.99.100
 - To kill all containers at once use: docker kill $(docker ps -q)
 - To remove all containters at once use:
    1. docker stop $(docker ps -a -q)
    2. docker rm $(docker ps -a -q)
 - To remove all none images use: docker rmi $(docker images | grep "^<none>" | awk "{print $3}")
 - To run mongodb in docker container use: docker run -it --rm --name lux-mongo -p 27017:27017 mongo
 - To remove all images use:
    1.docker rm $(docker ps -a -q) 
    2.docker rmi $(docker images -q)
 
 
     Hints for docker-compose interaction: 
 - docker-compose up -d
 - docker-compose ps
 - docker-compose stop
 - docker-compose up
 - docker-compose scale producer=5

 
    Vert.x shell useful interactions:
 - pwd
 - ls
 - cd
 - verticle-ls
 - verticle-undeploy
 - verticle-deploy
 - bus-tail story_topic
 - custom commands
  

    Running vert.x from command line
vertx run producer.groovy -cp consumer-0.1.jar:producer-0.1.jar:web-0.1.jar:cluster.xml -ha -cluster -cluster-host 10.0.1.2 -quorum 2

Custom cluster.xml is an override for hazelcast default config in order to use proper network interface as 
there could be several and hazelcast can choose any plus custom timeouts and delays should be configured in properties.
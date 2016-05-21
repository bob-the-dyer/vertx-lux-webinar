Hints:
 - To initialize docker environment run in terminal: eval "$(docker-machine env default)"
 - Docker bridge network is 172.17.0.X
 - to kill all containers in once use: docker kill $(docker ps -q)
 - to remove all containters in once use:
    1. docker stop $(docker ps -a -q)
    2. docker rm $(docker ps -a -q)
 - to remove all none images use:
 - to run mongodb in docker container use: docker run -it --name lux-mongo -p 27017:27017 mongo
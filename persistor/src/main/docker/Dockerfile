###
# To build:
#  docker build -t bnb/persistor .
# To run:
#   docker run -t -i bnb/persistor
###

FROM java:8-jre

ENV SPRING_BOOT_FILE persistor-0.1.jar

# Set the location of the spring boot apps
ENV BOOTAPPS_HOME /usr/bootapps

ENV REMOTE_JMX_OPTIONS -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=1898 -Dcom.sun.management.jmxremote.rmi.port=1898 -Djava.rmi.server.hostname='192.168.99.100'

# Set hazelcast options
# ENV HAZELCAST_OPTIONS -Dhazelcast.connection.monitor.max.faults=1 -Dhazelcast.connection.monitor.interval=1 -Dhazelcast.partition.migration.timeout=1 -Dhazelcast.connect.all.wait.seconds=5 -Dhazelcast.max.no.heartbeat.seconds=3
ENV HAZELCAST_OPTIONS -Dhazelcast.max.no.heartbeat.seconds=3 -Dhazelcast.merge.first.run.delay.seconds=3 -Dhazelcast.merge.next.run.delay.seconds=3 -Dhazelcast.max.join.merge.target.seconds=3

EXPOSE 4000

EXPOSE 1898

# Copy your fat jar to the container
COPY ./bootapps $BOOTAPPS_HOME

# Launch the boot app
WORKDIR $BOOTAPPS_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java $HAZELCAST_OPTIONS $REMOTE_JMX_OPTIONS -jar $SPRING_BOOT_FILE"]
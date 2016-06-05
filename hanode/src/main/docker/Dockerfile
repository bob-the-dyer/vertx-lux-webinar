FROM java:8-jre

ENV HANODE_FILE hanode-fat.jar

# Set the location of the hanode verticle
ENV HANODE_HOME /usr/verticles

# Set vertx command
ENV VERTX_RUN_COMMAND "bare"

# Here we need to expose all ports from all embedded verticles
EXPOSE 4000
EXPOSE 1898
EXPOSE 8080

# Copy your fat jar to the container
COPY ./verticles $HANODE_HOME

# Launch the boot app
WORKDIR $HANODE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -Djava.net.preferIPv4Stack=true -jar $HANODE_FILE $VERTX_RUN_COMMAND"]
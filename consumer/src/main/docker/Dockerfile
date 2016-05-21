###
# To build:
#  docker build -t bnb/consumer .
# To run:
#   docker run -t -i bnb/consumer
###

FROM java:8-jre

ENV VERTICLE_FILE consumer-0.1-fat.jar

# Set vertx option
ENV VERTX_OPTIONS ""

# Set the location of the verticles
ENV VERTICLE_HOME /usr/verticles

# Copy your fat jar to the container
COPY ./verticles $VERTICLE_HOME

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar $VERTICLE_FILE $VERTX_OPTIONS"]
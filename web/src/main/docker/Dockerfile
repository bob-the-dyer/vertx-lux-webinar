###
# To build:
#  docker build -t bnb/web .
# To run:
#   docker run -t -i bnb/web
###

FROM java:8-jre

ENV VERTICLE_FILE web-fat.jar

# Set vertx option
ENV VERTX_OPTIONS "-cluster"

# Set the location of the verticles
ENV VERTICLE_HOME /usr/verticles

EXPOSE 8080

# Copy your fat jar to the container
COPY ./verticles $VERTICLE_HOME

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar $VERTICLE_FILE $VERTX_OPTIONS"]
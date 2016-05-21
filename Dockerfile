###
# To build:
#  docker build -t lux/<vertical_name> .
# To run:
#   docker run -t -i -p 8080:8080 lux/<vertical_name>
###

# Extend vert.x image                       <1>
FROM vertx/vertx3

# Set the name of the verticle to deploy    <2>
ENV VERTICLE_NAME web_vertical.js

# Set the location of the verticles         <3>
ENV VERTICLE_HOME /usr/verticles

EXPOSE 8080

# Copy all src to the container             <4>
ADD src $VERTICLE_HOME/

# Launch the verticle                       <5>
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["vertx run $VERTICLE_NAME -cp $VERTICLE_HOME/*"]
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

EXPOSE 4000

# Copy your fat jar to the container
COPY ./bootapps $BOOTAPPS_HOME

# Launch the boot app
WORKDIR $BOOTAPPS_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar $SPRING_BOOT_FILE"]
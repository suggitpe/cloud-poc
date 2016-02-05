# Eureka Server
This server has been set up to run as a docker image.  To get this up and running you need to do the following from the base of this module:
 - `gradle` to build the application
 - `docker build -t eurekaserver .` to create the docker image (TODO blend into the gradle build)
 - `docker images` to see the images created
 - `docker run -d -p 8761:8761 eurekaserver` to start the docker container (exposing port 8761 as 8761) in teh background
 - `docker ps` to see the running containers
 - `docker ps | grep eurekaserver | cut -d" " -f1 | xargs docker stop` to stop the eurekaserver
 - `docker ps | grep eurekaserver | cut -d" " -f1 | xargs docker logs -f` to output the logs of the servers 

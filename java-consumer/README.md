# Simple Java Service
This server has been set up to run as a docker image.  To get this up and running you need to do the following from the base of this module:
 - `gradle` to build the application
 - `docker build -t java-consumer .` to create the docker image (TODO blend into the gradle build)
 - `docker images` to see the images created
 - `docker run -d -p 8902:8902 --net=host java-consumer` to start the docker container (exposing port 8761 as 8761) in teh background
 - `docker ps` to see the running containers
 - `docker ps | grep java-consumer | cut -d" " -f1 | xargs docker stop` to stop the consumer
 - `docker ps | grep java-consumer | cut -d" " -f1 | xargs docker logs -f` to output the logs of the servers 

# Simple Java Service
This server has been set up to run as a docker image.  To get this up and running you need to do the following from the base of this module:
 - `gradle` to build the application
 
## Docker
 - `docker build -t java-producer .` to create the docker image (TODO blend into the gradle build)
 - `docker images` to see the images created
 - `docker run -d -p 8901:8901 --net=host java-producer` to start the docker container (exposing port 8761 as 8761) in teh background
 - `docker ps` to see the running containers
 - `docker ps | grep java-producer | cut -d" " -f1 | xargs docker stop` to stop the service
 - `docker ps | grep java-producer | cut -d" " -f1 | xargs docker logs -f` to output the logs of the servers 
grad

## Cloudfoundry

* `cf push`
* `cf logs simple-service --recent`
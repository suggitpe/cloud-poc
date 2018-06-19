# Cloudfoundry PoC

## Whats in here
In this repo you will find a bunch of microservicey apps that serve the purpose of demonstrating how to build apps that work in a cloud environment.  They are a first go at them so assume they are not sufficiently good enough to release anywhere sensible.

## What are they

### EurekaServer
This is a super simple implementation of a Eureka Server (Netflix) that is used for service registry and location.

### Sample-Java-Service
This is a simple greeting app that when started, looks for a Eureka and registers itself as a service.  It does not have the capability to look up another service.

### EurekaClient
This is a java service that when employed will envoke the services of the java-service through the service registry.

## How do I use them
All parts of this project work with Gradle.  The basic operation for them all is
1. Build with `gradle`
2. Execute with `java -jar build/libs/<name>.jar`

## Docker container management
* docker rm `docker ps --no-trunc -aq` # to delete all containers
* docker stop `docker ps -q` # to stop all running containers
* `docker attach [container name]` to get a command line on the running container

## Docker-compose options
* `docker-compose up -d` to start the docker container in daemon mode
* `docker-compose down` to stop and remove all the containers

## Usefull cloudfoundry calls
* `cf delete-orphaned-routes`
* `cf delete app-name`
* `cf delete-service service-name`
* `cf logs service-name --recent`

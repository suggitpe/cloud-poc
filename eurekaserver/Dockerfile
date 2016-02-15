FROM java:8
VOLUME /tmp
VOLUME /target
ADD build/libs/eurekaserver-0.0.1.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]

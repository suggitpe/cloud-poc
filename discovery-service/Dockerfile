FROM suggitpe/centos-openjdk-base
VOLUME /tmp
VOLUME /target
ADD build/libs/discovery-service-*.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]






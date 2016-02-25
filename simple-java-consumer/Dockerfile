FROM suggitpe/centos7-orajava8-base
VOLUME /tmp
VOLUME /target
ENV JAVA_OPTS -Dspring.profiles.active=docker
ADD build/libs/simple-java-consumer-0.0.1.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8902
ENTRYPOINT ["java", "-jar", "/app.jar"]

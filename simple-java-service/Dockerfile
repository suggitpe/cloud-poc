FROM suggitpe/centos7-orajava8-base
VOLUME /tmp
VOLUME /target
ENV JAVA_OPTS -Dspring.profiles.active=docker
ADD build/libs/simple-java-service-*.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8901
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM suggitpe/centos-openjdk-base
VOLUME /tmp
VOLUME /target
ENV JAVA_OPTS -Dspring.profiles.active=docker
ADD build/libs/java-producer-*.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8901
ENTRYPOINT ["java", "-jar", "/app.jar"]

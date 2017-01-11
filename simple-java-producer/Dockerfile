FROM dockerbase_centos
VOLUME /tmp
VOLUME /target
ENV JAVA_OPTS -Dspring.profiles.active=docker
ADD build/libs/simple-java-producer-*.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8901
ENTRYPOINT ["java", "-jar", "/app.jar"]

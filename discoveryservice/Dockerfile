FROM suggitpe/18.04-orajava8-base
VOLUME /tmp
VOLUME /target
ADD build/libs/discoveryservice-*.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]






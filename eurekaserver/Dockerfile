FROM dockerbase_centos
VOLUME /tmp
VOLUME /target
ADD build/libs/eurekaserver-*.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]

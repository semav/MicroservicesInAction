FROM openjdk:8
RUN apk add --no-cache maven
RUN mvn package
ADD target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-server.jar"]

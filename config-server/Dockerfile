FROM maven:3.6.0-jdk-8-alpine as build
RUN mkdir -p /build

COPY pom.xml /build
COPY src /build/src

WORKDIR /build

RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jre-alpine
COPY --from=build /build/target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-server.jar"]

#FROM openjdk:8-jdk-alpine as build
#RUN apk add --no-cache maven

FROM maven:3.6.0-jdk-8-alpine as build
RUN mkdir -p /build

COPY pom.xml /build
COPY src /build/src

WORKDIR /build

#Download all required dependencies into one layer
#RUN mvn -B dependency:resolve dependency:resolve-plugins

RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jre-alpine
COPY --from=build /build/target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
#ADD app.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-server.jar"]

FROM maven:3.6.0-jdk-8-alpine as builder
RUN mkdir -p /build
WORKDIR /build

COPY pom.xml /build
COPY src /build/src

#Download all required dependencies into one layer
#RUN mvn -B dependency:resolve dependency:resolve-plugins
#Copy source code
# Build application
#RUN mvn package
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine
#COPY --from=builder /build/target/*.jar app.jar
ADD --from=builder /build/target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-server.jar"]

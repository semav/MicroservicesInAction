FROM maven:3.6.0-jdk-8-alpine AS MAVEN_TOOL_CHAIN 
COPY config-server/pom.xml /tmp/
COPY config-server/src /tmp/src/
WORKDIR /tmp/
RUN mvn package

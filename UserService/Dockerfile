FROM maven:3.5.2-jdk-8-alpine AS build

MAINTAINER tozzis

WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/
RUN mvn package

FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/UserService-0.0.1-SNAPSHOT.jar /app

EXPOSE 3001

ENTRYPOINT ["java", "-jar", "/app/UserService-0.0.1-SNAPSHOT.jar"]
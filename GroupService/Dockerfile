FROM maven:3.6.1-jdk-8-alpine as build

WORKDIR /app

COPY ./pom.xml /app

RUN mvn verify clean package --fail-never

COPY ./src /app/src

RUN mvn clean package -DskipTests

CMD ["sh", "-c", "java -jar /app/target/*.jar"]
FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /app/src
COPY pom.xml /app
COPY application.properties /app/src/main/resources

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM openjdk:17-alpine

COPY --from=maven-builder app/target/*.jar /app-service/app.jar
WORKDIR /app-service

EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
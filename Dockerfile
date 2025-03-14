FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar /app/java.jar

ENTRYPOINT ["java", "-jar", "/app/java.jar"]

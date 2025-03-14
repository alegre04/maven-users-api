FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/mi-aplicacion-java-1.0.jar /app/mi-aplicacion-java.jar

ENTRYPOINT ["java", "-jar", "/app/mi-aplicacion-java.jar"]

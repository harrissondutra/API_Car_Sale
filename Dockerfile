FROM ubuntu:latest
LABEL authors="Harrisson Dutra"

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Cars-0.0.1.jar /app/Cars-0.0.1.jar

ENTRYPOINT ["java", "-jar", "Cars-0.0.1.jar"]
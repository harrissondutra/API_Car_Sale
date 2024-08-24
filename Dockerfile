FROM ubuntu:latest
LABEL authors="Harrisson Dutra"

FROM eclipse-temurin:17-jre-alpine
RUN mkdir -p /api
COPY target/Cars-0.0.1.jar.jar /api/Cars-0.0.1.jar.jar
WORKDIR /api/
EXPOSE 8080
ENTRYPOINT java -jar Cars-0.0.1.jar.jar
# Используем мультиархитектурный базовый образ с Java 17 и Maven
FROM maven:3.8.4-openjdk-17 AS build

COPY ./src /app/amoCrmTask/src
COPY ./pom.xml /app/amoCrmTask/pom.xml
WORKDIR /app/amoCrmTask

RUN mvn clean package

FROM openjdk:17-jdk-slim

COPY --from=build /app/amoCrmTask/target/*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
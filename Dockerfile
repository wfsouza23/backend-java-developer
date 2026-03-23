# Imagem base com Java 25 (JDK)
FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

COPY target/*.jar app.jar

FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY --from=build /app/app.jar app.jar

EXPOSE 9012

ENTRYPOINT ["java", "-jar", "app.jar"]

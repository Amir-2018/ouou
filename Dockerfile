FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /workspace

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src

RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /workspace/target/ouou-accessories.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]
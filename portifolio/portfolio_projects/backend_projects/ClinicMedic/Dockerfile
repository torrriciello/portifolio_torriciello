FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

EXPOSE 3000

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
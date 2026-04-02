FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# copy jar
COPY target/*.jar app.jar

# expose port
EXPOSE 8080

# run app
ENTRYPOINT ["java", "-jar", "app.jar"]
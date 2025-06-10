# Use a slim OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR file into the container
COPY target/roomchatbackend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Proper spacing and syntax for ENTRYPOINT
ENTRYPOINT ["java", "-jar", "app.jar"]

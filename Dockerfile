# Stage 1: Build the JAR file using Gradle
FROM gradle:8.8-jdk21 AS builder

# Set the working directory for the Gradle build
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradle /app/gradle
COPY gradlew /app/
COPY build.gradle /app/
COPY settings.gradle /app/
COPY src /app/src

# Build the JAR file
RUN gradle clean build
# Stage 2: Create a runtime image
FROM openjdk:21-oracle

# Set the working directory in the runtime image
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=builder /app/build/libs/CitiesGame-0.0.1-SNAPSHOT.jar /app/CitiesGame-0.0.1-SNAPSHOT.jar

# Expose the port your Spring Boot application listens on
EXPOSE 8080

# Set the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "CitiesGame-0.0.1-SNAPSHOT.jar"]

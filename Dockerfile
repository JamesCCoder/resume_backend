# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jre

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container at /app
COPY target/backend-0.0.1-SNAPSHOT.jar /app/app.jar

# 复制 application.properties 文件到容器中
COPY src/main/resources/application.properties /app/application.properties

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.config.location=/app/application.properties"]

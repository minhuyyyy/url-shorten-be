# Use an OpenJDK image as the base
FROM openjdk:23

# Set working directory inside the container
WORKDIR /app

# Copy the application JAR file
COPY target/url_shortener-0.0.1-SNAPSHOT.jar app.jar

# Expose the port used by the application
EXPOSE 8088

# Run the application
CMD ["java", "-jar", "app.jar"]

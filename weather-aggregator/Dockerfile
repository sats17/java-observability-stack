FROM amazoncorretto:17

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/weather-aggregator-1.0.0.jar .

# Specify the entry point
ENTRYPOINT ["java", "-jar", "weather-aggregator-1.0.0.jar"]

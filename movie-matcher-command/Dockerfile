  # Start with a base image containing Java runtime
FROM amazoncorretto:21

  # The application's jar file
ARG JAR_FILE=build/libs/movie-matcher-command-0.0.1-SNAPSHOT.jar

  # Add the application's jar to the container
COPY ${JAR_FILE} app.jar

  # Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]

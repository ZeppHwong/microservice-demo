# Use an official OpenJDK runtime as a parent image
FROM openjdk:11

# Set the maintainer
MAINTAINER wangzhaopei

# Set the working directory to /app
COPY k8s-sample-v1.0.0.jar /user/local/k8s-sample.jar
WORKDIR /user/local/

# Copy the current directory contents into the container at /app


# Make port 18080 available to the world outside this container
EXPOSE 18088

# Run the application when the container launches
ENTRYPOINT ["java", "-jar", "k8s-sample.jar"]

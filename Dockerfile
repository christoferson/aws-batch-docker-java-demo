FROM ubuntu:22.10

# Install Open-JDK (JAVA)
RUN apt-get update && apt-get -y install openjdk-17-jdk

#copy from local to container and rename jar
COPY ./target/aws-batch-docker-java-1.0.0.jar /batch.jar
RUN chmod 777 batch.jar
# Run the  batch job
CMD ["java","-jar", "batch.jar"]

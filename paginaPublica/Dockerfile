FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/paginapublica-0.0.1.jar
COPY ${JAR_FILE} paginapublica.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","paginapublica.jar"]


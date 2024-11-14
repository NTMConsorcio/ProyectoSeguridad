FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/paginaPublica-0.0.1.jar
COPY ${JAR_FILE} paginaPublica.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","paginaPublica.jar"]


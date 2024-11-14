FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/clienteadministrativo-0.0.1.jar
COPY ${JAR_FILE} clienteadministrativo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","clienteadministrativo.jar"]
FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/is2-api-gateway.jar
COPY ${JAR_FILE} is2-api-gateway.jar
ENTRYPOINT ["java","-jar","/is2-api-gateway.jar"]

FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/is2-logger.jar
COPY ${JAR_FILE} is2-logger.jar
ENTRYPOINT ["java","-jar","/is2-logger.jar"]

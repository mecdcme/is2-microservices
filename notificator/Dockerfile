FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/is2-notificator.jar
COPY ${JAR_FILE} is2-notificator.jar
ENTRYPOINT ["java","-jar","/is2-notificator.jar"]

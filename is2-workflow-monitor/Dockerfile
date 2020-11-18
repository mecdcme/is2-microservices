FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/is2-workflow-monitor.jar
COPY ${JAR_FILE} is2-workflow-monitor.jar
ENTRYPOINT ["java","-jar","/is2-workflow-monitor.jar"]

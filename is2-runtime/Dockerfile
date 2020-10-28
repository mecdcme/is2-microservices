FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/is2-runtime.jar
COPY ${JAR_FILE} is2-runtime.jar
ENTRYPOINT ["java","-jar","/is2-runtime.jar"]

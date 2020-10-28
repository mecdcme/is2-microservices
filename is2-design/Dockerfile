FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/is2-design.jar
COPY ${JAR_FILE} is2-design.jar
ENTRYPOINT ["java","-jar","/is2-design.jar"]

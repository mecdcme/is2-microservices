FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/is2-eureka-server.jar
COPY ${JAR_FILE} is2-eureka-server.jar
ENTRYPOINT ["java","-jar","/is2-eureka-server.jar"]

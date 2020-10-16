FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/is2-worksession.jar
COPY ${JAR_FILE} is2-worksession.jar
ENTRYPOINT ["java","-jar","/is2-worksession.jar"]

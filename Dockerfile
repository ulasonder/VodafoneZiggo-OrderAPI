FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} orderapi.jar
ENTRYPOINT ["java","-jar","/orderapi.jar"]
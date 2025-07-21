# Java image
FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bazaarly.jar
ENTRYPOINT ["java", "-jar", "/bazaarly.jar"]


FROM openjdk:17-slim

COPY target/bazaarly.jar bazaarly.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "bazaarly.jar"]

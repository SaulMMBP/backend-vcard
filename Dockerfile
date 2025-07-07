FROM eclipse-temurin:21
ARG JAR_FILE=backend-vcard/target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
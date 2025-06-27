FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]



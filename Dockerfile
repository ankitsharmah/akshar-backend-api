FROM maven:3.9.7-sapmachine-22 as builder
COPY ./src src/
COPY ./pom.xml pom.xml
RUN mvn clean package -DskipTests

FROM openjdk:22-rc-jdk-slim-bookworm
COPY --from=builder target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
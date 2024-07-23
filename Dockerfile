FROM maven:3.8.3-openjdk-17
COPY target/spring-application.jar spring-application.jar
MAINTAINER kosstee
EXPOSE 8080
ENTRYPOINT ["java","-jar", "spring-application.jar"]
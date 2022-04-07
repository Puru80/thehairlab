FROM openjdk:17-jdk-alpine
LABEL maintainer="puru80"
VOLUME /main-app
ADD target/thehairlab-revisited-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar","/app.jar"]
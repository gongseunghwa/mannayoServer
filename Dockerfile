FROM openjdk:11-jdk
ARG JAR_FILE=/home/runner/work/mannayoServer/mannayoServer/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
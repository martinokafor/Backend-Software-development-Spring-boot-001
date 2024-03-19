FROM openjdk:20
ARG JAR_FILE=target/profile-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app-new.jar
ENTRYPOINT ["java","-jar","app-new.jar"]
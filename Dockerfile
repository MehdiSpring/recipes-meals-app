# For Java 11, try this
FROM adoptopenjdk:11-jre-hotspot

# Refer to Maven build -> finalName
ARG JAR_FILE=target/simple-app-jib-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR C:/Users/Home/Desktop/docker-image

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
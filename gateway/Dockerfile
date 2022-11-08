FROM openjdk:17-jdk-alpine
VOLUME /tmp
RUN gradle build
ARG JAR_FILE=build/libs/iwa_2022/gateway/build/libs/CSAL-gateway-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar"]

EXPOSE 5001

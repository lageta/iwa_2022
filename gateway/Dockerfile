

FROM gradle:7.5.1-jdk17-alpine as gradleimage
COPY . /home/gradle/gateway/source
WORKDIR /home/gradle/gateway/source
RUN gradle build

FROM openjdk:17-jdk-slim
COPY --from=gradleimage /home/gradle/gateway/source/build/libs/CSAL-gateway-0.0.1-SNAPSHOT.jar/ app/gateway/
WORKDIR /app/gateway
ENTRYPOINT ["java", "-jar", "CSAL-gateway-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

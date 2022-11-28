
FROM gradle:7.5.1-jdk17-alpine as gradleimage
COPY . /home/gradle/auth/source
WORKDIR /home/gradle/auth/source
RUN gradle build

FROM openjdk:17-jdk-slim
COPY --from=gradleimage /home/gradle/auth/source/build/libs/CSAL-auth-0.0.1-SNAPSHOT.jar/ app/auth/
WORKDIR /app/auth
ENTRYPOINT ["java", "-jar", "CSAL-auth-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082

FROM gradle:7.5.1-jdk17-alpine as gradleimage
COPY . /home/gradle/users/source
WORKDIR /home/gradle/users/source
RUN gradle check
RUN gradle build

FROM openjdk:17-jdk-slim
COPY --from=gradleimage /home/gradle/users/source/build/libs/CSAL-users-0.0.1-SNAPSHOT.jar/ app/users/
WORKDIR /app/users
ENTRYPOINT ["java", "-jar", "CSAL-users-0.0.1-SNAPSHOT.jar"]



FROM gradle:7.5.1-jdk17-alpine as gradleimage
COPY . /home/gradle/affect/source
WORKDIR /home/gradle/affect/source
RUN gradle check
RUN gradle build

FROM openjdk:17-jdk-slim
COPY --from=gradleimage /home/gradle/affect/source/build/libs/CSAL-affect-0.0.1-SNAPSHOT.jar/ app/affect/
WORKDIR /app/affect
ENTRYPOINT ["java", "-jar", "CSAL-affect-0.0.1-SNAPSHOT.jar"]


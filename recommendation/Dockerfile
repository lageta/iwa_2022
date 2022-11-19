FROM gradle:7.5.1-jdk17-alpine as gradleimage
COPY . /home/gradle/recommendation/source
WORKDIR /home/gradle/recommendation/source
RUN gradle check
RUN gradle build

FROM openjdk:17-jdk-slim
COPY --from=gradleimage /home/gradle/recommendation/source/build/libs/CSAL-Recommendation-0.0.1-SNAPSHOT.jar/ app/recommendation/
WORKDIR /app/recommendation
ENTRYPOINT ["java", "-jar", "CSAL-Recommendation-0.0.1-SNAPSHOT.jar"]



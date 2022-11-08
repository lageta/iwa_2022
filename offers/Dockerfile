FROM gradle:7.5.1-jdk17-alpine as gradleimage
COPY . /home/gradle/offers/source
WORKDIR /home/gradle/offers/source
RUN gradle build

FROM openjdk:17-jdk-slim
COPY --from=gradleimage /home/gradle/offers/source/build/libs/CSAL-offers-0.0.1-SNAPSHOT.jar/ app/offers/
WORKDIR /app/offers
ENTRYPOINT ["java", "-jar", "CSAL-offers-0.0.1-SNAPSHOT.jar"]
EXPOSE 8000

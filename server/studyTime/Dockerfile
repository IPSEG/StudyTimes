FROM gradle:7.6-jdk11 as build

WORKDIR /app
COPY . .
RUN gradle build -x test --parallel wrapper --gradle-version=7.6

FROM eclipse-temurin:11
WORKDIR /app
COPY --from=build /app/build/libs /app
ENTRYPOINT ["java", "-jar", "studyTime-0.0.1-SNAPSHOT.war"]
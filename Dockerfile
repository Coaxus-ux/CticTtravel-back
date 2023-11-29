FROM maven:3.8.4-openjdk-17-slim AS build
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install -DskipTests

FROM openjdk:17
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/cticTravel-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

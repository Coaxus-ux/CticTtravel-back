FROM openjdk:17

WORKDIR /app

COPY target/cticTravel-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

CMD ["java", "-jar", "app.jar"]
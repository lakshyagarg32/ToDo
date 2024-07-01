FROM openjdk:21-jdk

RUN mkdir app

COPY target/ToDo-1.0-SNAPSHOT.jar app
COPY example.yml app

EXPOSE 8080

WORKDIR app

CMD ["java", "-jar", "ToDo-1.0-SNAPSHOT.jar", "server", "example.yml"]

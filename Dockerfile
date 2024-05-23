FROM openjdk:17

WORKDIR /usr/src/myapp

COPY . .

RUN ["./mvnw", "clean", "package", "-DskipTests"]

CMD ["java", "-jar", "target/pitschallenge-0.0.1-SNAPSHOT.jar"]


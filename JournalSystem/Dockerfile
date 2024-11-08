
# Steg 1: Använd en officiell Java runtime-bild
FROM openjdk:17-jdk-alpine

# Steg 2: Ställ in arbetskatalogen i containern
WORKDIR /app

# Steg 3: Kopiera jar-filen till containern
# Byt ut 'JournalSystem-backend.jar' mot ditt faktiska jar-filnamn
COPY target/JournalSystem-backend-0.0.1-SNAPSHOT.jar app.jar

# Steg 4: Exponera porten som Spring Boot använder
EXPOSE 8080

# Steg 5: Definiera kommandot för att köra applikationen
ENTRYPOINT ["java", "-jar", "app.jar"]

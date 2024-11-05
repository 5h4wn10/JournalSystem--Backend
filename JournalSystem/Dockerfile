# Använd en officiell OpenJDK-bild som basbild
FROM openjdk:11-jdk-slim

# Sätt arbetskatalogen i containern
WORKDIR /app

# Kopiera JAR-filen från den lokala `target`-mappen till containern
ARG JAR_FILE=target/journalsystem-backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Exponera porten som din applikation körs på
EXPOSE 8080

# Kommando för att köra applikationen
ENTRYPOINT ["java", "-jar", "app.jar"]

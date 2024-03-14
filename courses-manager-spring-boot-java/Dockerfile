# image de base Alpine Linux
FROM openjdk:17-alpine
# Définir l'emplacement de l'application
WORKDIR /app
# Copie de l'application JAR dans l'image Docker
COPY target/courses-manager-spring-boot-java-0.0.1-SNAPSHOT.jar /app/app.jar
# Commande par défaut pour lancer l'application
CMD ["java", "-jar", "app.jar"]

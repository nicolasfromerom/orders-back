# Usa una imagen base ligera con Java 21
FROM eclipse-temurin:21-jdk-alpine
# Directorio de trabajo
WORKDIR /app
# Copia el JAR generado desde Maven (ruta relativa al contexto)
COPY target/*.jar app.jar
# Expone el puerto 8080
EXPOSE 8080
# Comando de inicio
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

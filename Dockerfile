# Etapa 1: Compilar la aplicación usando Maven y Java 25
FROM maven:3.9-eclipse-temurin-25-alpine AS build
WORKDIR /app

# Copiar el archivo de configuración de dependencias
COPY pom.xml .

# Descargar las dependencias para acelerar futuras compilaciones
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar el archivo .jar omitiendo los tests
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen ligera de ejecución con Java 25
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Copiar el archivo .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto estándar de Spring Boot
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
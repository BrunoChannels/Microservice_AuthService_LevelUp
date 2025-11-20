# --- Etapa 1: Construcción (Build) ---
# Usamos una imagen oficial de Maven con JDK 24
FROM maven:3.9-eclipse-temurin-24 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los archivos necesarios para descargar dependencias
COPY .mvn/ .mvn/
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# Damos permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw

# Descargamos las dependencias (se cachea si el pom.xml no cambia)
RUN ./mvnw dependency:go-offline

# Copiamos el resto del código fuente
COPY src ./src

# Compilamos la aplicación y omitimos los tests
RUN ./mvnw package -DskipTests

# --- Etapa 2: Ejecución (Run) ---
# Usamos una imagen de JRE 24 (Java Runtime Environment) ligera basada en Alpine
FROM eclipse-temurin:24-jre-alpine

# Establecemos el directorio de trabajo
WORKDIR /app

# Exponemos el puerto 8080 (Debe coincidir con server.port en application.properties)
EXPOSE 8080

# Copiamos el archivo .jar compilado desde la etapa 'build'
COPY --from=build /app/target/*.jar app.jar

# El comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
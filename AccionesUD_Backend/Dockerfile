FROM eclipse-temurin:21-jdk-alpine
# Establece el directorio de trabajo
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Descarga las dependencias (haciendo caché en la capa Docker)
RUN ./mvnw dependency:go-offline
COPY src ./src
# Comando para ejecutar la aplicación
CMD ["./mvnw", "spring-boot:run"]
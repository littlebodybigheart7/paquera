FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# ── Etapa 2: Imagem final leve ────────────────────────
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Nome do JAR bate com artifactId + version do pom.xml
COPY --from=build /app/target/aula1-0.0.1-SNAPSHOT.jar app.jar

# O Render sobrescreve PORT via env var; EXPOSE é apenas documentação
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]

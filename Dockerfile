# Etapa 1: Build da aplicação
FROM maven:3.9.2-eclipse-temurin-22-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final com JDK
FROM eclipse-temurin:22-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Definindo variáveis de ambiente (se necessário)
ENV SPRING_PROFILES_ACTIVE=prod

# Porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

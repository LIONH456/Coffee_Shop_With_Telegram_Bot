# syntax=docker/dockerfile:1

# -------- Build stage --------
FROM gradle:8.8-jdk17-alpine AS build
WORKDIR /home/gradle/project

# Cache dependencies first
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
RUN gradle --no-daemon build -x test || true

# Copy the rest of the source
COPY . .
RUN gradle --no-daemon clean build -x test

# -------- Runtime stage --------
FROM eclipse-temurin:17-jre-alpine
ENV SPRING_PROFILES_ACTIVE=render
ENV JAVA_OPTS=""
WORKDIR /app

# Copy built jar
COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar

# Render provides PORT env var
ENV PORT=8080
EXPOSE 8080

CMD ["sh", "-c", "java $JAVA_OPTS -Dserver.port=$PORT -jar /app/app.jar"]



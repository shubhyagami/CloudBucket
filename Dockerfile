### multi-stage Dockerfile for building and running the CloudBucket Spring Boot app
### Build stage: use Maven with JDK 17
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace

# copy maven wrapper and pom first for better layer caching
COPY mvnw pom.xml .
COPY .mvn .mvn
RUN chmod +x ./mvnw

# copy only sources and build
COPY src ./src

# build the application (skip tests to speed up builds, change if you want tests)
RUN ./mvnw -DskipTests package -P!integration-tests


### Run stage: lightweight JRE
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# copy the fat jar from the build stage
COPY --from=build /workspace/target/*.jar ./app.jar

# allow Java options injection
ENV JAVA_OPTS=""

# Render provides the port via the PORT env var. Spring Boot already reads server.port=${PORT:8080}
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD wget -qO- --timeout=2 http://localhost:${PORT:-8080}/ || exit 1

CMD java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar

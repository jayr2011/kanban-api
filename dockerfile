FROM maven:3.9.6-eclipse-temurin-19 AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:19-jre-jammy
WORKDIR /app

COPY --from=builder /app/target/kanban-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

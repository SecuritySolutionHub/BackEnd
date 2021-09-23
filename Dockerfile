FROM adoptopenjdk/openjdk11 AS builder
WORKDIR /app

# COPY gradle file
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew; sync && ./gradlew
RUN ./gradlew dependencies

# COPY FILE
COPY . .

RUN ./gradlew clean build

FROM adoptopenjdk/openjdk11
WORKDIR /app

COPY --from=builder /app/build/libs/solution_hub.jar solution_hub.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "./solution_hub.jar" ]
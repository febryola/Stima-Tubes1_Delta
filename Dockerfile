# Build stage
FROM maven:3.8.4-openjdk-17 AS build

COPY src /build/src
COPY pom.xml /build
COPY bot.json /build

RUN mvn -f /build/pom.xml install
RUN mv /build/bin/Delta-jar-with-dependencies.jar /build/bin/Delta.jar

# Runner Stage
FROM openjdk:17-slim

RUN apt update
RUN apt install wget unzip make -y
RUN wget https://github.com/EntelectChallenge/2020-Overdrive/releases/download/2020.3.4/starter-pack.zip

RUN unzip starter-pack.zip
RUN mv starter-pack app

WORKDIR /app

RUN mkdir bots
RUN mv ./reference-bot/java ./bots/second-player

COPY config/game-runner-config.json ./game-runner-config.json
COPY config/game-config.json ./game-config.json

COPY --from=build /build /app/bots/first-player

CMD ["make", "run"]
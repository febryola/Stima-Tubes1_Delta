# Build stage
FROM maven:3.8.4-openjdk-17 AS build

COPY src /build/src
COPY pom.xml /build
RUN make

# Runner Stage
FROM openjdk:17-slim

RUN wget https://github.com/EntelectChallenge/2020-Overdrive/releases/download/2020.3.4/starter-pack.zip
RUN apt install unzip

RUN unzip starter-pack.zip
RUN mv starter-pack app

WORKDIR /app

RUN mkdir bots
RUN mv -r ./starter-bots ./bots/second-player

COPY --from=build /build/bin/Delta.jar /app/bot/first-player/Delta.jar

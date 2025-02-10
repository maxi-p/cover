FROM eclipse-temurin:17-jdk-focal as build

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN chmod +x /app/mvnw

RUN ./mvnw clean package -DskipTests

FROM debian:bullseye-slim

# Install LaTeX dependencies
RUN apt-get update && apt-get install -y \
    texlive-latex-base \
    texlive-fonts-recommended \
    texlive-latex-extra \
    texlive-fonts-extra \
    texlive-lang-english \
    openjdk-17-jre \
    && apt-get clean

COPY --from=build /app/target/cover_gen-0.0.1-SNAPSHOT.jar /app/cover_gen-0.0.1-SNAPSHOT.jar

EXPOSE 9090

CMD java -jar /app/cover_gen-0.0.1-SNAPSHOT.jar

FROM eclipse-temurin:23-noble AS builder

WORKDIR /src

# copy files
COPY mvnw .
COPY pom.xml .

COPY .mvn .mvn
COPY src src

# make mvnw executable
RUN chmod a+x mvnw && /src/mvnw package -Dmaven.test.skip=true

FROM eclipse-temurin:23-jre-noble

WORKDIR /app

COPY --from=builder /src/target/movies-0.0.1-SNAPSHOT.jar app.jar

# COPY --from=compiler /app/movies_post_2010.zip /app/movies_post_2010.zip

# COPY --from=compiler /app/director_movies_report.jasper /app/director_movies_report.jasper

ENV PORT=8080
ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/movies
ENV SPRING_DATASOURCE_USERNAME=fred
ENV SPRING_DATASOURCE_PASSWORD=fred

ENV SPRING_DATA_MONGODB_URI=mongodb://host.docker.internal:27017/movies

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar
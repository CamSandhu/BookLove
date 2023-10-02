FROM maven:3.8.3-openjdk-11 AS build
COPY ..
RUN mvn clean package -DskipTests

FROM maven:3.8.3-jdk-11-slim
COPY --from=build /target/PenguinPublishers-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
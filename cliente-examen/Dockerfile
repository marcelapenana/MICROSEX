FROM openjdk:18-jdk-alpine as builder
#FROM openjdk:18-alpine as builder
WORKDIR /app/cliente-examen
#COPY ./cliente-examen/target/cliente-examen-0.0.1-SNAPSHOT.jar .
#descarga dependecias

COPY ./pom.xml /app
COPY ./cliente-examen/.mvn ./.mvn
COPY ./cliente-examen/mvnw .
COPY ./cliente-examen/pom.xml .
#COPY ./cliente-examen/. m

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN chmod a+x ./mvnw
RUN sed -i -e 's/\r$//' ./mvnw

# RUN ./mvnw dependency:go-offline
COPY ./cliente-examen/src ./src
RUN ./mvnw clean package -DskipTests
EXPOSE 8001

FROM openjdk:18-alpine

WORKDIR /app/
COPY --from=builder /app/cliente-examen/target/cliente-examen-0.0.1-SNAPSHOT.jar .

#COPY --from=builder /app/cliente-examen/target/cliente-examen-0.0.1-SNAPSHOT.jar .

#ENTRYPOINT ["java","-jar", "./target/cliente-examen-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-jar", "cliente-examen-0.0.1-SNAPSHOT.jar"]

#docker build -t usuarios . -f .\cliente-examen\Dockerfile
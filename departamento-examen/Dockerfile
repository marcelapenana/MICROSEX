FROM openjdk:18-jdk-alpine as builder
#FROM openjdk:18-alpine as builder
WORKDIR /app/departamento-examen
#COPY /departamento-examen/target/departamento-examen-0.0.1-SNAPSHOT.jar .
#descarga dependecias

COPY ./pom.xml /app
COPY ./departamento-examen/.mvn ./.mvn
COPY ./departamento-examen/mvnw .
COPY ./departamento-examen/pom.xml .
#COPY ./departamento-examen/. m

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN chmod a+x ./mvnw
RUN sed -i -e 's/\r$//' ./mvnw

# RUN ./mvnw dependency:go-offline
COPY ./departamento-examen/src ./src
RUN ./mvnw clean package -DskipTests
EXPOSE 8002

FROM openjdk:18-alpine

WORKDIR /app/

COPY --from=builder /app/departamento-examen/target/departamento-examen-0.0.1-SNAPSHOT.jar .


ENTRYPOINT ["java","-jar", "departamento-examen-0.0.1-SNAPSHOT.jar"]


#docker build -t departamentos . -f .\departamento-examen\Dockerfile
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /opt/app
COPY .m2 .m2
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw -s .m2/settings.xml dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]

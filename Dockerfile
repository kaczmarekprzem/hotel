FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY src ./src

COPY run.sh .

RUN chmod +x run.sh

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

CMD ["./run.sh"]

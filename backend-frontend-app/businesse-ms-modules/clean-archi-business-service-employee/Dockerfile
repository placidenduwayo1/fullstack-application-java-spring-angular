FROM maven:3.8.3-openjdk-17-slim AS builder
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src
RUN mvn package -DskipTest
WORKDIR /build/target/
RUN java -Djarmode=layertools -jar clean-archi-business-service-employee.jar extract

FROM maven:3.8.3-openjdk-17-slim
COPY --from=builder /build/target/dependencies/ ./
COPY --from=builder /build/target/spring-boot-loader/ ./
COPY --from=builder /build/target/snapshot-dependencies/ ./
COPY --from=builder /build/target/application/ ./

EXPOSE 8283
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
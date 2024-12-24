FROM openjdk:17-jdk-slim

COPY build/libs/case-opener-1.0.1-SNAPSHOT.jar case-opener.jar

EXPOSE 4040

ENTRYPOINT ["java", "-jar", "case-opener.jar"]
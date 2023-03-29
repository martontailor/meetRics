FROM ghcr.io/graalvm/graalvm-ce:22.3.1
RUN gu install python
ADD target/meetrics-0.0.1-SNAPSHOT.jar app.jar
ADD venv venv
EXPOSE 8080:8080
ENTRYPOINT ["java","-jar","app.jar"]
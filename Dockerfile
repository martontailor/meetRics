FROM vegardit/graalvm-maven:22.3.1-java17
RUN gu install python
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
ADD home/app/target/meetrics-0.0.1-SNAPSHOT.jar app.jar
ADD home/app/venv venv
EXPOSE 8080:8080
ENTRYPOINT ["java","-jar","app.jar"]
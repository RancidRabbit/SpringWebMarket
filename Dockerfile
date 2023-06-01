FROM java:openjdk-8u111-jdk-alpine
ARG JAR_FILE=target/webApp-0.0.*-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
CMD java, -jar, app.jar
EXPOSE 8050
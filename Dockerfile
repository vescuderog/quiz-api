FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/quiz-*.jar
COPY ${JAR_FILE} quiz.jar
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-Djava.security.egd=file:/dev/./urandom","-jar","/quiz.jar"]

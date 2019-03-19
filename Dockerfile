FROM openjdk:8-jdk-alpine
VOLUME /tmp

ARG JAR_FILE=target/quiz-*.jar
COPY ${JAR_FILE} quiz.jar

# Expose is NOT supported by Heroku
# EXPOSE 8080

ENTRYPOINT ["java"]

# CMD is required to run on Heroku
# $PORT is set by Heroku			
CMD ["-Djava.security.egd=file:/dev/./urandom","-Dserver.port=$PORT","-jar","/quiz.jar"]

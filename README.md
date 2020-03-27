# Quiz API

[![Build status](https://app.wercker.com/status/a4441a77f675040ee7f5941830dc986c/s/ "wercker status")](https://app.wercker.com/project/byKey/a4441a77f675040ee7f5941830dc986c)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=com.lapots.breed.judge:judge-rule-engine&metric=alert_status)](https://sonarcloud.io/dashboard?id=quiz-api)
[![Heroku](https://img.shields.io/badge/Heroku-success-green)](https://api-quiz-app-docker.herokuapp.com/swagger-ui.html)
[![Docker Automated build](https://img.shields.io/docker/automated/vescuderog/quiz-api)](https://hub.docker.com/r/vescuderog/quiz-api)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Quiz API is a Spring Boot application.

This project was created to learn how we can use [Wercker](https://app.wercker.com/) for Continuous Integration and Continuous Deployment (CI/CD) of a Spring Boot application. We use Wercker pipeline to run maven build goals, run unit tests and integration tests in parallel, check code quality using [SonarCloud](https://sonarcloud.io/projects), build Docker image and push it to [DockerHub](https://hub.docker.com/), deploy it to [Heroku](https://www.heroku.com/) and finally, run the [acceptance tests](https://github.com/vescuderog/quiz-api-qa).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

### Installing

```bash
mvn install
```

## Usage

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.merkone.quiz.app.boot.QuizApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```bash
mvn spring-boot:run
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot/)
* [Swagger](https://swagger.io/)
* [Liquibase](https://www.liquibase.org/)
* [MapStruct](https://mapstruct.org/)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)

package com.merkone.quiz.app.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author vescudero
 */
@Configuration
@ComponentScan(basePackages = {
    "com.merkone.**.service",
    "com.merkone.api.quiz.server",
    "com.merkone.quiz.app.configuration",
    "com.merkone.quiz.app.exception",
    "com.merkone.quiz.repository"
})
@EntityScan("com.merkone.quiz.domain")
@EnableJpaRepositories("com.merkone.quiz.repository")
@Import({SwaggerDocumentationConfig.class})
public class ApplicationConfig {

}

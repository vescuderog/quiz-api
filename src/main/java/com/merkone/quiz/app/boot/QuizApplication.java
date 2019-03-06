package com.merkone.quiz.app.boot;

import com.merkone.quiz.app.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author vescudero
 */
@SpringBootApplication
@EnableSwagger2
@Import({ApplicationConfig.class})
public class QuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizApplication.class, args);
    }

}

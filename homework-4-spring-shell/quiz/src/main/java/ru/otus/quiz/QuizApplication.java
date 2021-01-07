package ru.otus.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.quiz.config.QuizProperty;
import ru.otus.quiz.service.application.ShellApplication;

@SpringBootApplication
@EnableConfigurationProperties(QuizProperty.class)
public class QuizApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(QuizApplication.class, args);
    }
}

package ru.otus.quiz.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "quiz")
public class QuizProperty {
    private Locale locale;
    private int countCorrectAnswers;
    private String questionBasename;
}

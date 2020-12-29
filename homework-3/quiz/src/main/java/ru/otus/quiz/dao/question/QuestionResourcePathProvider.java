package ru.otus.quiz.dao.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.quiz.config.QuizProperty;

import java.util.Locale;

@Component
public class QuestionResourcePathProvider {

    private Locale locale;

    @Autowired
    public QuestionResourcePathProvider(QuizProperty quizProperty) {
        this.locale = quizProperty.getLocale();
    }

    public String getQuestionResourcePath() {
        return String.format("/i18n/quiz/quiz_%s%s", locale.toString(), ".csv");
    }
}

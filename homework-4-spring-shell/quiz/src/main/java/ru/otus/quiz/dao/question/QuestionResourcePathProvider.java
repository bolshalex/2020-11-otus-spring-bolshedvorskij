package ru.otus.quiz.dao.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.quiz.config.QuizProperty;

import java.util.Locale;

@Component
public class QuestionResourcePathProvider {

    private final Locale locale;
    private final String baseName;

    @Autowired
    public QuestionResourcePathProvider(QuizProperty quizProperty) {
        this.locale = quizProperty.getLocale();
        this.baseName = quizProperty.getQuestionBasename() + "_%s%s";
    }

    public String getQuestionResourcePath() {
        return String.format(baseName, locale.toString(), ".csv");
    }
}

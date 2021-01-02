package ru.otus.quiz.dao.question;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.quiz.config.QuizProperty;

import java.util.Locale;

class QuestionResourcePathProviderTest {

    @DisplayName("Verifying that the question path is provided correctly")
    @Test
    void getQuestionResourcePath() {
        QuizProperty quizProperty = new QuizProperty();
        quizProperty.setLocale(new Locale("ru"));
        quizProperty.setQuestionBasename("/i18n/quiz/questions");
        QuestionResourcePathProvider questionResourcePathProvider = new QuestionResourcePathProvider(quizProperty);

        String actualPath = questionResourcePathProvider.getQuestionResourcePath();
        String expectedPath = "/i18n/quiz/questions_ru.csv";
        Assertions.assertThat(actualPath).isEqualTo(expectedPath);
    }
}
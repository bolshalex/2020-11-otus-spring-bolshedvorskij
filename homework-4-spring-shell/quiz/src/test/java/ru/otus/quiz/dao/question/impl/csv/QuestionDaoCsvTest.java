package ru.otus.quiz.dao.question.impl.csv;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.quiz.config.QuizProperty;
import ru.otus.quiz.dao.question.QuestionDao;
import ru.otus.quiz.dao.question.QuestionResourcePathProvider;
import ru.otus.quiz.dao.question.exception.QuestionDaoException;
import ru.otus.quiz.domain.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuestionDaoCsvTest {
    private static final String TEST_QUESTION_PATH = "/dao/quiz.csv";
    @MockBean
    private CsvQuestionParser csvQuestionParser;
    @MockBean
    private QuestionResourcePathProvider questionResourcePathProvider;

    @Autowired
    private QuestionDao questionDao;

    @Test
    void getQuestions() throws QuestionDaoException {
        when(csvQuestionParser.parseCsvRecord(any())).thenCallRealMethod();
        when(questionResourcePathProvider.getQuestionResourcePath()).thenReturn(TEST_QUESTION_PATH);

        List<Question> actualQuestions = questionDao.getQuestions();
        List<Question> expectedQuestions = getExpectedQuestions();
        Assertions.assertThat(actualQuestions).isEqualTo(expectedQuestions);
    }

    @Test
    void testThrowException() {
        QuizProperty quizProperty = new QuizProperty();
        quizProperty.setCountCorrectAnswers(1);

        QuestionDao questionDao = new QuestionDaoCsv(questionResourcePathProvider, csvQuestionParser);
        assertThrows(QuestionDaoException.class, questionDao::getQuestions);
    }

    private List<Question> getExpectedQuestions() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2);
        questions.add(question);
        return questions;
    }

    @ComponentScan(basePackageClasses = {QuestionDao.class})
    @Configuration
    static class TestConfiguration {

    }
}
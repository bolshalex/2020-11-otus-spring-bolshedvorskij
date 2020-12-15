package ru.otus.quiz.dao.question.impl.csv;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.quiz.dao.question.QuestionDao;
import ru.otus.quiz.dao.question.exception.QuestionDaoException;
import ru.otus.quiz.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionDaoCsvTest {
    private static final String TEST_QUESTION_PATH = "/ru/otus/quiz/dao/question/quiz.csv";

    @Test
    void getQuestions() throws QuestionDaoException {
        QuestionDao questionDao = new QuestionDaoCsv(TEST_QUESTION_PATH);
        List<Question> questions = questionDao.getQuestions();

        List<Question> expectedQuestions = getExpectedQuestions();
        Assertions.assertThat(questions).isEqualTo(expectedQuestions);
    }

    @Test
    void testThrowException() {
        QuestionDao questionDao = new QuestionDaoCsv("");
        assertThrows(QuestionDaoException.class, () -> questionDao.getQuestions());
    }

    private List<Question> getExpectedQuestions() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2);
        questions.add(question);
        return questions;
    }
}
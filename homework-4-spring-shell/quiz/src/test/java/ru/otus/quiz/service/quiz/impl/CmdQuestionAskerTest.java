package ru.otus.quiz.service.quiz.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.quiz.domain.io.AnswerValidator;
import ru.otus.quiz.domain.io.LocalizedIoService;
import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.Question;
import ru.otus.quiz.service.quiz.QuestionAsker;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@SpringBootTest
class CmdQuestionAskerTest {

    @MockBean
    private LocalizedIoService localizedIoService;
    @Autowired
    private QuestionAsker questionAsker;

    @BeforeEach
    void setUp() throws Exception {
        lenient().when(localizedIoService.askInteger(anyString(), any(AnswerValidator.class))).thenReturn(2);
    }

    @Test
    void quizProcess() throws QuizServiceException {
        List<PlayerAnswer> playerAnswers = questionAsker.quizProcess(getQuestions());
        List<PlayerAnswer> expectedPlayerAnswers = getExpectedPlayerAnswers();
        Assertions.assertThat(playerAnswers).isEqualTo(expectedPlayerAnswers);
    }

    private List<Question> getQuestions() {

        return Collections.singletonList(new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2));
    }

    private List<PlayerAnswer> getExpectedPlayerAnswers() {
        Question question = new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2);
        return Collections.singletonList(new PlayerAnswer(question, 2));

    }

    @ComponentScan(basePackageClasses = {QuestionAsker.class})
    @Configuration
    static class TestConfiguration {
    }
}
package ru.otus.quiz.service.quiz.impl;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.quiz.domain.io.AnswerValidator;
import ru.otus.quiz.domain.io.IoService;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.PlayerAnswers;
import ru.otus.quiz.domain.model.Question;
import ru.otus.quiz.service.player.PlayerService;
import ru.otus.quiz.service.player.exception.PlayerServiceException;
import ru.otus.quiz.service.question.QuestionService;
import ru.otus.quiz.service.quiz.QuizProcessor;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest
class CmdQuizProcessorTest {

    @MockBean
    private QuestionService questionService;
    @MockBean
    private PlayerService playerService;
    @MockBean
    private IoService ioService;
    @Autowired
    private QuizProcessor quizProcessor;

    @BeforeEach
    void setUp() throws Exception {
        lenient().when(questionService.getQuestions()).thenReturn(getQuestions());
        lenient().when(playerService.getPlayer()).thenReturn(new Player("Player1"));
        lenient().when(ioService.askInteger(anyString(),
                any(AnswerValidator.class), anyString())).thenReturn(2);
    }

    @Test
    void quizProcess() throws QuizServiceException {
        PlayerAnswers playerAnswers = quizProcessor.quizProcess();

        PlayerAnswers expectedPlayerAnswers = getExpectedPlayerAnswers();
        Assertions.assertThat(playerAnswers).isEqualTo(expectedPlayerAnswers);
    }

    @SneakyThrows
    @Test
    void testThrowException() {
        when(playerService.getPlayer()).thenThrow(new PlayerServiceException());
        assertThrows(QuizServiceException.class, () -> quizProcessor.quizProcess());
    }

    private List<Question> getQuestions() {

        return Collections.singletonList(new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2));
    }

    private PlayerAnswers getExpectedPlayerAnswers() {
        Player player = new Player("Player1");
        Question question = new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2);

        List<PlayerAnswer> answers = Collections.singletonList(new PlayerAnswer(question, 2));
        return new PlayerAnswers(player, answers);
    }
}
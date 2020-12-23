package ru.otus.quiz.service.quiz.impl;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.quiz.domain.io.ask.Asker;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CmdQuizProcessorTest {

    @Mock
    private QuestionService questionService;
    @Mock
    private PlayerService playerService;
    @Mock
    private Asker asker;

    @BeforeEach
    void setUp() throws Exception {
        lenient().when(questionService.getQuestions()).thenReturn(getQuestions());
        lenient().when(playerService.getPlayer()).thenReturn(new Player("Player1"));
        lenient().when(asker.askInteger(anyString())).thenReturn(2);
    }

    @Test
    void quizProcess() throws QuizServiceException {
        QuizProcessor quizProcessor = new CmdQuizProcessor(questionService, playerService, asker);
        PlayerAnswers playerAnswers = quizProcessor.quizProcess();

        PlayerAnswers expectedPlayerAnswers = getExpectedPlayerAnswers();
        Assertions.assertThat(playerAnswers).isEqualTo(expectedPlayerAnswers);
    }

    @SneakyThrows
    @Test
    void testThrowException() {
        when(playerService.getPlayer()).thenThrow(new PlayerServiceException());
        QuizProcessor quizProcessor = new CmdQuizProcessor(questionService, playerService, asker);
        assertThrows(QuizServiceException.class, () -> quizProcessor.quizProcess());
    }

    private List<Question> getQuestions() {

        return Arrays.asList(new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2));
    }

    private PlayerAnswers getExpectedPlayerAnswers() {
        Player player = new Player("Player1");
        Question question = new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2);

        List<PlayerAnswer> answers = Arrays.asList(new PlayerAnswer(question, 2));
        return new PlayerAnswers(player, answers);
    }
}
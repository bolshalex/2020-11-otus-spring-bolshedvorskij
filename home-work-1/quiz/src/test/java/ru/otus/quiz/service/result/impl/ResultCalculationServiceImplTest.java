package ru.otus.quiz.service.result.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.quiz.model.Player;
import ru.otus.quiz.model.PlayerAnswer;
import ru.otus.quiz.model.Question;
import ru.otus.quiz.model.QuizResult;
import ru.otus.quiz.service.result.ResultCalculationService;

import java.util.*;

class ResultCalculationServiceImplTest {
    @Test
    void calcResult() {
        ResultCalculationService resultCalculationService = new ResultCalculationServiceImpl(1);
        Map<Player, QuizResult> playerQuizResult = resultCalculationService.calcResult(getPlayerAnswers());
        Assertions.assertThat(playerQuizResult).isEqualTo(getExpectedPlayerResult());
    }

    private Map<Player, List<PlayerAnswer>> getPlayerAnswers() {
        Player player = new Player("Player1");
        Map<Player, List<PlayerAnswer>> playerAnswers = new HashMap<>();
        playerAnswers.put(player, getAnswers());
        return playerAnswers;
    }

    private List<PlayerAnswer> getAnswers() {
        List<PlayerAnswer> answers = new ArrayList<>();
        Question godFatherQuestion = new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2);
        Question iphoneQuestion = new Question("In what year was the first iPhone released?",
                Arrays.asList("2008", "2006", "2007", "1999"),
                3);

        answers.add(new PlayerAnswer(godFatherQuestion, 2));
        answers.add(new PlayerAnswer(iphoneQuestion, 2));

        return answers;
    }

    private Map<Player, QuizResult> getExpectedPlayerResult() {
        Player player = new Player("Player1");
        QuizResult quizResult = new QuizResult(1, true);
        Map<Player, QuizResult> playerQuizResult = new HashMap<>();
        playerQuizResult.put(player, quizResult);
        return playerQuizResult;
    }
}
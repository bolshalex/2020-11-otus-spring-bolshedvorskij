package ru.otus.quiz.service.result.impl;

import ru.otus.quiz.model.Player;
import ru.otus.quiz.model.PlayerAnswer;
import ru.otus.quiz.model.QuizResult;
import ru.otus.quiz.service.result.ResultCalculationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultCalculationServiceImpl implements ResultCalculationService {
    private final int countCorrectAnswers;

    public ResultCalculationServiceImpl(int countCorrectAnswers) {
        this.countCorrectAnswers = countCorrectAnswers;
    }

    @Override
    public Map<Player, QuizResult> calcResult(Map<Player, List<PlayerAnswer>> playerAnswers) {

        Map<Player, QuizResult> playerQuizResultMap = new HashMap<>();

        for (Map.Entry<Player, List<PlayerAnswer>> answerEntry : playerAnswers.entrySet()) {
            playerQuizResultMap.put(answerEntry.getKey(), calcRightAnswers(answerEntry.getValue()));
        }
        return playerQuizResultMap;
    }

    private QuizResult calcRightAnswers(List<PlayerAnswer> answers) {
        int countPlayerCorrectAnswer = 0;
        for (PlayerAnswer answer : answers) {
            int correctAnswer = answer.getQuestion().getCorrectAnswer();
            if (answer.getAnswer() == correctAnswer) {
                countPlayerCorrectAnswer++;
            }
        }
        return new QuizResult(countPlayerCorrectAnswer,
                countPlayerCorrectAnswer == countCorrectAnswers);
    }

}

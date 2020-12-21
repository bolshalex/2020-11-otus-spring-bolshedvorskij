package ru.otus.quiz.service.result.impl;

import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.PlayerAnswers;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.result.ResultCalculationService;

public class ResultCalculationServiceImpl implements ResultCalculationService {
    private final int countCorrectAnswers;

    public ResultCalculationServiceImpl(int countCorrectAnswers) {
        this.countCorrectAnswers = countCorrectAnswers;
    }

    @Override
    public QuizResult calcResult(PlayerAnswers playerAnswers) {
        int countPlayerCorrectAnswer = 0;
        for (PlayerAnswer answer : playerAnswers.getPlayerAnswers()) {
            int correctAnswer = answer.getQuestion().getCorrectAnswer();
            if (answer.getAnswer() == correctAnswer) {
                countPlayerCorrectAnswer++;
            }
        }
        return new QuizResult(playerAnswers.getPlayer(),
                countPlayerCorrectAnswer,
                countPlayerCorrectAnswer == countCorrectAnswers);
    }


}

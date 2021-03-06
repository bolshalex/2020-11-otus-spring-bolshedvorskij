package ru.otus.quiz.service.result.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.config.QuizProperty;
import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.result.ResultCalculationService;

import java.util.List;

@Service
public class ResultCalculationServiceImpl implements ResultCalculationService {
    private final int countCorrectAnswers;

    @Autowired
    public ResultCalculationServiceImpl(QuizProperty quizProperty) {
        this.countCorrectAnswers = quizProperty.getCountCorrectAnswers();
    }

    @Override
    public QuizResult calcResult(List<PlayerAnswer> playerAnswers) {
        int countPlayerCorrectAnswer = 0;
        for (PlayerAnswer answer : playerAnswers) {
            int correctAnswer = answer.getQuestion().getCorrectAnswer();
            if (answer.getAnswer() == correctAnswer) {
                countPlayerCorrectAnswer++;
            }
        }
        return new QuizResult(
                countPlayerCorrectAnswer,
                countPlayerCorrectAnswer >= countCorrectAnswers);
    }


}

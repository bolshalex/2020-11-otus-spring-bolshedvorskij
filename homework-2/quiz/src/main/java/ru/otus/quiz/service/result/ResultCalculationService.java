package ru.otus.quiz.service.result;

import ru.otus.quiz.domain.model.PlayerAnswers;
import ru.otus.quiz.domain.model.QuizResult;

public interface ResultCalculationService {

    QuizResult calcResult(PlayerAnswers playerAnswers);
}

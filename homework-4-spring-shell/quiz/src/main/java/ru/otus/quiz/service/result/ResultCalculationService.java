package ru.otus.quiz.service.result;

import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.PlayerAnswers;
import ru.otus.quiz.domain.model.QuizResult;

import java.util.List;

public interface ResultCalculationService {

    QuizResult calcResult(List<PlayerAnswer> playerAnswers);
}

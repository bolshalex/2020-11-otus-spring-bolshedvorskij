package ru.otus.quiz.service.result;

import ru.otus.quiz.model.Player;
import ru.otus.quiz.model.PlayerAnswer;
import ru.otus.quiz.model.QuizResult;

import java.util.List;
import java.util.Map;

public interface ResultCalculationService {

    Map<Player, QuizResult> calcResult(Map<Player, List<PlayerAnswer>> playerAnswers);
}

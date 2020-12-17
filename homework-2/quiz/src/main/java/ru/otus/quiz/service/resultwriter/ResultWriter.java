package ru.otus.quiz.service.resultwriter;

import ru.otus.quiz.model.Player;
import ru.otus.quiz.model.QuizResult;

import java.util.Map;

public interface ResultWriter {

    void writeResult(Map<Player, QuizResult> playerResult);
}

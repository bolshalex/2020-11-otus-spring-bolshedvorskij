package ru.otus.quiz.service.resultwriter;

import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.QuizResult;

public interface ResultWriter {

    void writeResult(Player player, QuizResult playerResult);
}

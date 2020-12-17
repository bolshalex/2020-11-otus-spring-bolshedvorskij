package ru.otus.quiz.service.quiz;

import ru.otus.quiz.model.Player;
import ru.otus.quiz.model.PlayerAnswer;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;

import java.util.List;
import java.util.Map;

public interface QuizProcessor {

    /**
     * @return
     */
    Map<Player, List<PlayerAnswer>> quizProcess() throws QuizServiceException;
}

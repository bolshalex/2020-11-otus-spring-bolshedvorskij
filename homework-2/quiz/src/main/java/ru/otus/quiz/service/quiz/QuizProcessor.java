package ru.otus.quiz.service.quiz;

import ru.otus.quiz.domain.model.PlayerAnswers;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;

public interface QuizProcessor {

    /**
     * @return
     */
    PlayerAnswers quizProcess() throws QuizServiceException;
}

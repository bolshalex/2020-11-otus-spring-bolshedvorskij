package ru.otus.quiz.service.quiz;

import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.Question;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;

import java.util.List;

public interface QuestionAsker {

    List<PlayerAnswer> quizProcess(List<Question> questions) throws QuizServiceException;
}

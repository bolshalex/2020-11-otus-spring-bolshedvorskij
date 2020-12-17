package ru.otus.quiz.service.question;

import ru.otus.quiz.dao.question.exception.QuestionDaoException;
import ru.otus.quiz.model.Question;
import ru.otus.quiz.service.question.exception.QuestionServiceException;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions() throws QuestionServiceException;
}

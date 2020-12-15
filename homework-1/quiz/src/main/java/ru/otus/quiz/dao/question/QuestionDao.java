package ru.otus.quiz.dao.question;

import ru.otus.quiz.dao.question.exception.QuestionDaoException;
import ru.otus.quiz.model.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getQuestions() throws QuestionDaoException;
}

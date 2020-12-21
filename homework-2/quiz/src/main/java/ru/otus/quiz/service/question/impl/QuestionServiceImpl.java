package ru.otus.quiz.service.question.impl;

import ru.otus.quiz.dao.question.QuestionDao;
import ru.otus.quiz.dao.question.exception.QuestionDaoException;
import ru.otus.quiz.domain.model.Question;
import ru.otus.quiz.service.question.QuestionService;
import ru.otus.quiz.service.question.exception.QuestionServiceException;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestions() throws QuestionServiceException {
        try {
            return questionDao.getQuestions();
        } catch (QuestionDaoException e) {
            throw new QuestionServiceException(e);
        }
    }
}

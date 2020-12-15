package ru.otus.quiz.dao.question.exception;

public class QuestionDaoException extends Exception {
    public QuestionDaoException() {
        super();
    }

    public QuestionDaoException(String message) {
        super(message);
    }

    public QuestionDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionDaoException(Throwable cause) {
        super(cause);
    }
}

package ru.otus.quiz.service.question.exception;

public class QuestionServiceException extends Exception {
    public QuestionServiceException() {
        super();
    }

    public QuestionServiceException(String message) {
        super(message);
    }

    public QuestionServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionServiceException(Throwable cause) {
        super(cause);
    }
}

package ru.otus.quiz.service.quiz.exception;

public class QuizServiceException extends Exception {
    public QuizServiceException() {
        super();
    }

    public QuizServiceException(String message) {
        super(message);
    }

    public QuizServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuizServiceException(Throwable cause) {
        super(cause);
    }
}

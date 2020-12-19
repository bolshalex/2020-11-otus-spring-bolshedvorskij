package ru.otus.quiz.service.result.exception;

public class ResultServiceException extends Exception{
    public ResultServiceException() {
        super();
    }

    public ResultServiceException(String message) {
        super(message);
    }

    public ResultServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultServiceException(Throwable cause) {
        super(cause);
    }
}

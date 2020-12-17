package ru.otus.quiz.service.resultwriter.exception;

public class ResultWriterException extends Exception {
    public ResultWriterException() {
        super();
    }

    public ResultWriterException(String message) {
        super(message);
    }

    public ResultWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultWriterException(Throwable cause) {
        super(cause);
    }
}

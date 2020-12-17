package ru.otus.quiz.dao.player.exception;

public class PlayerDaoException extends Exception {
    public PlayerDaoException() {
        super();
    }

    public PlayerDaoException(String message) {
        super(message);
    }

    public PlayerDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerDaoException(Throwable cause) {
        super(cause);
    }
}

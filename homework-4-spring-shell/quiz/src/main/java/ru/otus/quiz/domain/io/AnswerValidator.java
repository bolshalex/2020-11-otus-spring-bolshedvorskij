package ru.otus.quiz.domain.io;

public interface AnswerValidator<T> {

    /**
     * Check user answer
     *
     * @param answer - user answer
     * @return true - ok
     */
    boolean check(T answer);
}

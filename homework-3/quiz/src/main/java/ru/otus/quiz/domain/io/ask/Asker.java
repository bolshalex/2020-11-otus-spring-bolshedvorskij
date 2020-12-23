package ru.otus.quiz.domain.io.ask;

public interface Asker {

    String askNotEmptyString(String message);

    int askInteger(String message);
}

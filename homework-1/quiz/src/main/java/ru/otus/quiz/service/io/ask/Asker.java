package ru.otus.quiz.service.io.ask;

public interface Asker {

    String askNotEmptyString(String message);

    int askInteger(String message);
}

package ru.otus.quiz.domain.io;

public interface IoService {


    String askNotEmptyString(String message, String emptyAnswerMessage);

    int askInteger(String message);

    int askInteger(String message, AnswerValidator<Integer> validator, String invalidAnswerMessage);

    void print(String string);
}

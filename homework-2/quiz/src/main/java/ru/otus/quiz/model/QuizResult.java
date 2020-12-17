package ru.otus.quiz.model;

import lombok.Data;

@Data
public class QuizResult {
    private final int countRightAnswers;
    private final boolean isSuccessful;
}

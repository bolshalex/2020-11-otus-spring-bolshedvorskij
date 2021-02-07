package ru.otus.quiz.domain.model;

import lombok.Data;

@Data
public class QuizResult {
    private final int countRightAnswers;
    private final boolean isSuccessful;
}

package ru.otus.quiz.domain.model;

import lombok.Data;

@Data
public class PlayerAnswer {
    private final Question question;
    private final int answer;
}

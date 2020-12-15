package ru.otus.quiz.model;

import lombok.Data;

import java.util.List;

@Data
public class PlayerAnswer {
    private final Question question;
    private final int answer;
}

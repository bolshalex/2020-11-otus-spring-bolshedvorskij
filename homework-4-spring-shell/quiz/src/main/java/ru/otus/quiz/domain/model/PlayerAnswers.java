package ru.otus.quiz.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class PlayerAnswers {
    private final Player player;
    private final List<PlayerAnswer> playerAnswers;
}

package ru.otus.quiz.domain.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Question {
    private String question;
    private List<String> answerVariants;
    private int correctAnswer;
}

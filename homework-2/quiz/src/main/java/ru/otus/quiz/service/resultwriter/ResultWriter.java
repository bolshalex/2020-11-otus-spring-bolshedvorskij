package ru.otus.quiz.service.resultwriter;

import ru.otus.quiz.domain.model.QuizResult;

public interface ResultWriter {

    void writeResult(QuizResult playerResult);
}

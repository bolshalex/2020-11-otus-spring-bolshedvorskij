package ru.otus.quiz.service.resultwriter.impl;

import ru.otus.quiz.model.Player;
import ru.otus.quiz.model.QuizResult;
import ru.otus.quiz.service.io.out.Printer;
import ru.otus.quiz.service.resultwriter.ResultWriter;

import java.util.Map;

public class ResultWriterImpl implements ResultWriter {
    private static final String TEST_SUCCESSFUL = "%s, тест пройден. Количество правильных ответов: %d";
    private static final String TEST_FAIL = "%s, тест не пройден. Количество правильных ответов: %d";

    private final Printer printer;

    public ResultWriterImpl(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void writeResult(Map<Player, QuizResult> playerResult) {

        for (Map.Entry<Player, QuizResult> playerQuizResultEntry : playerResult.entrySet()) {
            Player player = playerQuizResultEntry.getKey();
            QuizResult quizResult = playerQuizResultEntry.getValue();
            String template = quizResult.isSuccessful() ? TEST_SUCCESSFUL : TEST_FAIL;
            String stringResult = String.format(template, player.getName(), quizResult.getCountRightAnswers());
            printer.print(stringResult);
        }
    }
}

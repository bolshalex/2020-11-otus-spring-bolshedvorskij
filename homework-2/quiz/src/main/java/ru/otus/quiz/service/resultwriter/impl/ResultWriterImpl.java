package ru.otus.quiz.service.resultwriter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.io.out.Printer;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.resultwriter.ResultWriter;

@Service
public class ResultWriterImpl implements ResultWriter {
    private static final String TEST_SUCCESSFUL = "%s, test is successful. Number of correct answers: %d";
    private static final String TEST_FAIL = "%s, test failed. Number of correct answers: %d";

    private final Printer printer;

    @Autowired
    public ResultWriterImpl(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void writeResult(QuizResult playerResult) {


        Player player = playerResult.getPlayer();
        String template = playerResult.isSuccessful() ? TEST_SUCCESSFUL : TEST_FAIL;
        String stringResult = String.format(template, player.getName(), playerResult.getCountRightAnswers());
        printer.print(stringResult);
    }

}

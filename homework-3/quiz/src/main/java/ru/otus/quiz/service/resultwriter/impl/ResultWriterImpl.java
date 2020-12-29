package ru.otus.quiz.service.resultwriter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.LocalizedMessageProvider;
import ru.otus.quiz.domain.io.out.Printer;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.resultwriter.ResultWriter;

@Service
public class ResultWriterImpl implements ResultWriter {
    private static final String TEST_SUCCESSFUL = "%s, test is successful. Number of correct answers: %d";
    private static final String TEST_FAIL = "%s, test failed. Number of correct answers: %d";

    private final Printer printer;
    private final LocalizedMessageProvider messageProvider;

    @Autowired
    public ResultWriterImpl(Printer printer, LocalizedMessageProvider messageProvider) {
        this.printer = printer;
        this.messageProvider = messageProvider;
    }

    @Override
    public void writeResult(QuizResult playerResult) {
        Player player = playerResult.getPlayer();

        String resultMessage = getResultMessage(playerResult.isSuccessful() ? "test.successful" : "test.fail",
                player.getName(),
                String.valueOf(playerResult.getCountRightAnswers())
        );
        printer.print(resultMessage);
    }

    private String getResultMessage(String messageCode, String... args) {
        return messageProvider.getMessage(messageCode, args);
    }

}

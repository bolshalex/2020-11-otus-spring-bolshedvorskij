package ru.otus.quiz.service.resultwriter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.io.LocalizedIoService;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.resultwriter.ResultWriter;

@Service
public class ResultWriterImpl implements ResultWriter {

    private final LocalizedIoService localizedIoService;

    @Autowired
    public ResultWriterImpl(LocalizedIoService localizedIoService) {
        this.localizedIoService = localizedIoService;
    }

    @Override
    public void writeResult(QuizResult playerResult) {
        Player player = playerResult.getPlayer();

        localizedIoService.printMessage(playerResult.isSuccessful() ? "test.successful" : "test.fail",
                player.getName(),
                String.valueOf(playerResult.getCountRightAnswers())
        );
    }

}

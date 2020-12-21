package ru.otus.quiz.service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.model.PlayerAnswers;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.quiz.QuizProcessor;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;
import ru.otus.quiz.service.result.ResultCalculationService;
import ru.otus.quiz.service.resultwriter.ResultWriter;

@Service
public class ApplicationRunner {
    private final QuizProcessor quizProcessor;
    private final ResultWriter resultWriter;
    private final ResultCalculationService resultCalculationService;

    @Autowired
    public ApplicationRunner(QuizProcessor quizProcessor,
                             ResultWriter resultWriter,
                             ResultCalculationService resultCalculationService) {
        this.quizProcessor = quizProcessor;
        this.resultWriter = resultWriter;
        this.resultCalculationService = resultCalculationService;
    }

    public void run() {

        try {
            PlayerAnswers playerAnswers = quizProcessor.quizProcess();
            QuizResult quizResult = resultCalculationService.calcResult(playerAnswers);
            resultWriter.writeResult(quizResult);
        } catch (QuizServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}

package ru.otus.quiz.service.application;

import ru.otus.quiz.service.application.exception.ApplicationException;
import ru.otus.quiz.service.quiz.QuizProcessor;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;
import ru.otus.quiz.service.result.ResultCalculationService;
import ru.otus.quiz.service.resultwriter.ResultWriter;

public class Application {
    private final QuizProcessor quizProcessor;
    private final ResultWriter resultWriter;
    private final ResultCalculationService resultCalculationService;

    public Application(QuizProcessor quizProcessor, ResultWriter resultWriter, ResultCalculationService resultCalculationService) {
        this.quizProcessor = quizProcessor;
        this.resultWriter = resultWriter;
        this.resultCalculationService = resultCalculationService;
    }

    public void run() throws ApplicationException {

        try {
            resultWriter.writeResult(resultCalculationService.calcResult(quizProcessor.quizProcess()));
        } catch (QuizServiceException e) {
            throw new ApplicationException(e);
        }
    }
}

package ru.otus.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.quiz.dao.question.QuestionDao;
import ru.otus.quiz.dao.question.impl.csv.QuestionDaoCsv;
import ru.otus.quiz.domain.io.ask.Asker;
import ru.otus.quiz.domain.io.ask.impl.CmdAsker;
import ru.otus.quiz.domain.io.out.Printer;
import ru.otus.quiz.domain.io.out.impl.CmdPrinter;
import ru.otus.quiz.service.application.ApplicationRunner;
import ru.otus.quiz.service.player.PlayerService;
import ru.otus.quiz.service.player.impl.PlayerServiceImpl;
import ru.otus.quiz.service.question.QuestionService;
import ru.otus.quiz.service.question.impl.QuestionServiceImpl;
import ru.otus.quiz.service.quiz.QuizProcessor;
import ru.otus.quiz.service.quiz.impl.CmdQuizProcessor;
import ru.otus.quiz.service.result.ResultCalculationService;
import ru.otus.quiz.service.result.impl.ResultCalculationServiceImpl;
import ru.otus.quiz.service.resultwriter.ResultWriter;
import ru.otus.quiz.service.resultwriter.impl.ResultWriterImpl;

import java.io.InputStream;
import java.io.OutputStream;

@PropertySource("classpath:application.properties")
@Configuration
public class Config {
    @Bean
    public Printer getPrinter(@Value("#{ T(java.lang.System).out}") OutputStream outputStream) {
        return new CmdPrinter(outputStream);
    }

    @Bean
    public Asker getAsker(@Value("#{ T(java.lang.System).in}") InputStream inputStream,
                          Printer printer) {
        return new CmdAsker(inputStream, printer);
    }


    @Bean
    public QuestionDao questionDao(@Value("${quizProperty.questionPath}") String questionFilePath) {
        return new QuestionDaoCsv(questionFilePath);
    }

    @Bean
    public QuestionService getQuestionService(QuestionDao questionDao) {
        return new QuestionServiceImpl(questionDao);
    }

    @Bean
    public PlayerService getPlayerService(Asker asker) {
        return new PlayerServiceImpl(asker);
    }

    @Bean
    public QuizProcessor getQuizProcessor(QuestionService questionService,
                                          PlayerService playerService,
                                          Asker asker) {
        return new CmdQuizProcessor(questionService, playerService, asker);
    }

    @Bean
    public ResultWriter getResultWriter(Printer printer) {
        return new ResultWriterImpl(printer);
    }

    @Bean
    public ResultCalculationService getResultCalculationService(@Value("${quizProperty.countCorrectAnswers}") int countCorrectAnswers) {
        return new ResultCalculationServiceImpl(countCorrectAnswers);
    }

    @Bean
    public ApplicationRunner getApplication(QuizProcessor quizProcessor,
                                            ResultWriter resultWriter,
                                            ResultCalculationService resultCalculationService) {
        return new ApplicationRunner(quizProcessor, resultWriter, resultCalculationService);
    }

}

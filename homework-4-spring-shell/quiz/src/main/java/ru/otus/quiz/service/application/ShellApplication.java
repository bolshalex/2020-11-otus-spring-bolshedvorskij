package ru.otus.quiz.service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.quiz.dao.question.QuestionDao;
import ru.otus.quiz.dao.question.exception.QuestionDaoException;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.Question;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.quiz.QuestionAsker;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;
import ru.otus.quiz.service.result.ResultCalculationService;
import ru.otus.quiz.service.resultwriter.ResultWriter;

import javax.validation.constraints.Size;
import java.util.List;

@ShellComponent
public class ShellApplication {

    private final QuestionAsker questionAsker;
    private final ResultWriter resultWriter;
    private final ResultCalculationService resultCalculationService;
    private final QuestionDao questionDao;

    private Player player;

    @Autowired
    public ShellApplication(QuestionAsker questionAsker,
                            ResultWriter resultWriter,
                            ResultCalculationService resultCalculationService,
                            QuestionDao questionDao) {
        this.questionAsker = questionAsker;
        this.resultWriter = resultWriter;
        this.resultCalculationService = resultCalculationService;
        this.questionDao = questionDao;
    }

    @ShellMethod(value = "player registry", key = {"r", "registry"})
    public void registry(@Size(min = 1) String name) {
        player = new Player(name);
    }

    @ShellMethod(value = "run quiz", key = {"run", "start"})
    @ShellMethodAvailability("checkPlayerRegistry")
    public void run() {
        try {
            List<Question> questionList = questionDao.getQuestions();
            List<PlayerAnswer> playerAnswers = questionAsker.quizProcess(questionList);
            QuizResult quizResult = resultCalculationService.calcResult(playerAnswers);
            resultWriter.writeResult(player, quizResult);
        } catch (QuizServiceException | QuestionDaoException e) {
            System.out.println(e.getMessage());
        }
    }

    private Availability checkPlayerRegistry() {
        return player != null ? Availability.available()
                : Availability.unavailable("you are not registered");

    }
}

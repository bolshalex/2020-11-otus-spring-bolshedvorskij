package ru.otus.quiz.service.quiz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.io.ask.Asker;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.PlayerAnswers;
import ru.otus.quiz.domain.model.Question;
import ru.otus.quiz.service.player.PlayerService;
import ru.otus.quiz.service.player.exception.PlayerServiceException;
import ru.otus.quiz.service.question.QuestionService;
import ru.otus.quiz.service.question.exception.QuestionServiceException;
import ru.otus.quiz.service.quiz.QuizProcessor;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CmdQuizProcessor implements QuizProcessor {
    private final QuestionService questionService;
    private final PlayerService playerService;
    private final Asker asker;

    @Autowired
    public CmdQuizProcessor(QuestionService questionService, PlayerService playerService, Asker asker) {
        this.questionService = questionService;
        this.playerService = playerService;
        this.asker = asker;
    }

    @Override
    public PlayerAnswers quizProcess() throws QuizServiceException {
        Player player = getPlayer();
        List<Question> questions = loadQuestions();
        List<PlayerAnswer> answers = askQuestions(questions);
        return new PlayerAnswers(player, answers);
    }

    private Player getPlayer() throws QuizServiceException {
        Player player;
        try {
            player = playerService.getPlayer();
        } catch (PlayerServiceException e) {
            throw new QuizServiceException(e);
        }
        return player;
    }

    private List<Question> loadQuestions() throws QuizServiceException {
        List<Question> questions;
        try {
            questions = questionService.getQuestions();
        } catch (QuestionServiceException e) {
            throw new QuizServiceException(e);
        }
        return questions;
    }

    private List<PlayerAnswer> askQuestions(List<Question> questions) {
        List<PlayerAnswer> playerAnswers = new ArrayList<>();
        for (Question question : questions) {
            playerAnswers.add(askQuestion(question));
        }
        return playerAnswers;
    }

    private PlayerAnswer askQuestion(Question question) {
        List<String> variants = question.getAnswerVariants();

        int answer = asker.askInteger(buildQuestionString(question));
        while (answer < 0 || answer > variants.size()) {
            answer = asker.askInteger("Invalid response number. try again");
        }
        return new PlayerAnswer(question, answer);
    }

    private String buildQuestionString(Question question) {
        List<String> variants = question.getAnswerVariants();
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(question.getQuestion());
        for (int i = 0; i < variants.size(); i++) {
            stringBuffer.append(String.format("%n %d) %s", i + 1, variants.get(i)));
        }
        return stringBuffer.toString();
    }
}

package ru.otus.quiz.service.quiz.impl;

import ru.otus.quiz.model.Player;
import ru.otus.quiz.model.PlayerAnswer;
import ru.otus.quiz.model.Question;
import ru.otus.quiz.service.io.ask.Asker;
import ru.otus.quiz.service.player.PlayerService;
import ru.otus.quiz.service.player.exception.PlayerServiceException;
import ru.otus.quiz.service.question.QuestionService;
import ru.otus.quiz.service.question.exception.QuestionServiceException;
import ru.otus.quiz.service.quiz.QuizProcessor;
import ru.otus.quiz.service.quiz.exception.QuizServiceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CmdQuizProcessor implements QuizProcessor {
    private final QuestionService questionService;
    private final PlayerService playerService;
    private final Asker asker;

    public CmdQuizProcessor(QuestionService questionService, PlayerService playerService, Asker asker) {
        this.questionService = questionService;
        this.playerService = playerService;
        this.asker = asker;
    }

    @Override
    public Map<Player, List<PlayerAnswer>> quizProcess() throws QuizServiceException {
        Player player;
        try {
            player = playerService.getPlayer();
        } catch (PlayerServiceException e) {
            throw new QuizServiceException(e);
        }
        List<Question> questions;

        try {
            questions = questionService.getQuestions();
        } catch (QuestionServiceException e) {
            throw new QuizServiceException(e);
        }

        List<PlayerAnswer> answers = askQuestions(questions);
        Map<Player, List<PlayerAnswer>> playerAnswers = new HashMap<>();
        playerAnswers.put(player, answers);
        return playerAnswers;
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
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(question.getQuestion());
        for (int i = 0; i < variants.size(); i++) {
            stringBuffer.append(String.format("%n %d) %s", i + 1, variants.get(i)));
        }

        int answer = asker.askInteger(stringBuffer.toString());
        while (answer < 0 || answer > variants.size()) {
            answer = asker.askInteger("Невалидный номер ответа. Попробуте еще раз");
        }
        return new PlayerAnswer(question, answer);
    }
}

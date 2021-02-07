package ru.otus.quiz.service.quiz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.io.LocalizedIoService;
import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.Question;
import ru.otus.quiz.service.quiz.QuestionAsker;

import java.util.ArrayList;
import java.util.List;

@Service
public class CmdQuestionAsker implements QuestionAsker {
    private final LocalizedIoService localizedIoService;

    @Autowired
    public CmdQuestionAsker(LocalizedIoService localizedIoService) {
        this.localizedIoService = localizedIoService;
    }

    @Override
    public List<PlayerAnswer> quizProcess(List<Question> questions) {
        List<PlayerAnswer> answers = askQuestions(questions);
        return answers;
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

        int answer = localizedIoService.askInteger(buildQuestionString(question),
                userAnswer -> {
                    int intValue = userAnswer;
                    return intValue > 0 && intValue <= variants.size();
                });

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

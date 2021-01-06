package ru.otus.quiz.service.question;

import ru.otus.quiz.domain.model.Question;
import ru.otus.quiz.service.question.exception.QuestionServiceException;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions() throws QuestionServiceException;
}

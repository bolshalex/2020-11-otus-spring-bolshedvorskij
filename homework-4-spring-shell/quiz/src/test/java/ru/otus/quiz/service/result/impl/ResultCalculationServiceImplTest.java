package ru.otus.quiz.service.result.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.quiz.config.QuizProperty;
import ru.otus.quiz.domain.model.PlayerAnswer;
import ru.otus.quiz.domain.model.Question;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.result.ResultCalculationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class ResultCalculationServiceImplTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideTestData")
    void testQuizResult(String testName,
                        int requiredCorrectedAnswers,
                        QuizResult expectedPlayerQuizResult) {

        QuizProperty quizProperty = new QuizProperty();
        quizProperty.setCountCorrectAnswers(requiredCorrectedAnswers);
        ResultCalculationService resultCalculationService = new ResultCalculationServiceImpl(quizProperty);
        QuizResult playerQuizResult = resultCalculationService.calcResult(getAnswers());
        Assertions.assertThat(playerQuizResult).isEqualTo(expectedPlayerQuizResult);
    }


    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of("Test successful quiz", 1, getSuccessfulPlayerResult()),
                Arguments.of("Test fail quiz", 2, getFailPlayerResult())
        );
    }

    private List<PlayerAnswer> getAnswers() {
        List<PlayerAnswer> answers = new ArrayList<>();
        Question godFatherQuestion = new Question("In which year was The Godfather first released?",
                Arrays.asList("1933", "1972", "1990"),
                2);
        Question iphoneQuestion = new Question("In what year was the first iPhone released?",
                Arrays.asList("2008", "2006", "2007", "1999"),
                3);

        answers.add(new PlayerAnswer(godFatherQuestion, 2));
        answers.add(new PlayerAnswer(iphoneQuestion, 2));

        return answers;
    }

    private static QuizResult getSuccessfulPlayerResult() {
        return new QuizResult(1, true);
    }

    private static QuizResult getFailPlayerResult() {
        return new QuizResult(1, false);
    }
}
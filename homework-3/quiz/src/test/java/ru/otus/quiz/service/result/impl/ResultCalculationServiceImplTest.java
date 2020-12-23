package ru.otus.quiz.service.result.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.quiz.domain.model.*;
import ru.otus.quiz.service.result.ResultCalculationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ResultCalculationServiceImplTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideTestData")
    void testQuizResult(String testName,
                        int requiredCorrectedAnswers,
                        QuizResult expectedPlayerQuizResult) {

        ResultCalculationService resultCalculationService = new ResultCalculationServiceImpl(requiredCorrectedAnswers);
        QuizResult playerQuizResult = resultCalculationService.calcResult(getPlayerAnswers());
        Assertions.assertThat(playerQuizResult).isEqualTo(expectedPlayerQuizResult);
    }

    private PlayerAnswers getPlayerAnswers() {
        Player player = new Player("Player1");
        return new PlayerAnswers(player, getAnswers());
    }

    private Stream<Arguments> provideTestData() {
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

    private QuizResult getSuccessfulPlayerResult() {
        Player player = new Player("Player1");
        return new QuizResult(player, 1, true);
    }

    private QuizResult getFailPlayerResult() {
        Player player = new Player("Player1");
        return new QuizResult(player, 1, false);
    }
}
package ru.otus.quiz.service.resultwriter.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.quiz.domain.io.out.Printer;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.resultwriter.ResultWriter;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ResultWriterImplTest {

    @Mock
    private Printer printer;

    @Test
    void writeResult() {
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(printer).print(valueCapture.capture());
        ResultWriter resultWriter = new ResultWriterImpl(printer);
        resultWriter.writeResult(getPlayerQuizResult());
        String result = valueCapture.getValue();
        String expectedResult = "Player1, test is successful. Number of correct answers: 1";
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    private QuizResult getPlayerQuizResult() {
        Player player = new Player("Player1");
        return new QuizResult(player, 1, true);
    }
}
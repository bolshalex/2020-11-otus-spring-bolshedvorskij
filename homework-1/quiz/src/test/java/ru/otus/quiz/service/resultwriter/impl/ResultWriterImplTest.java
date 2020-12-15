package ru.otus.quiz.service.resultwriter.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.quiz.model.Player;
import ru.otus.quiz.model.QuizResult;
import ru.otus.quiz.service.io.out.Printer;
import ru.otus.quiz.service.resultwriter.ResultWriter;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
        String expectedResult = "Player1, тест пройден. Количество правильных ответов: 1";
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    private Map<Player, QuizResult> getPlayerQuizResult() {
        Player player = new Player("Player1");
        QuizResult quizResult = new QuizResult(1, true);
        Map<Player, QuizResult> playerQuizResult = new HashMap<>();
        playerQuizResult.put(player, quizResult);
        return playerQuizResult;
    }
}
package ru.otus.quiz.service.resultwriter.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.quiz.domain.LocalizedMessageProvider;
import ru.otus.quiz.domain.io.out.Printer;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.resultwriter.ResultWriter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class ResultWriterImplTest {

    @MockBean
    private Printer printer;
    @MockBean
    private LocalizedMessageProvider messageProvider;
    @Autowired
    private ResultWriter resultWriter;

    @Test
    void writeResult() {
        when(messageProvider.getMessage(anyString(), any()))
                .thenReturn("Player1, test is successful. Number of correct answers: 1");

        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(printer).print(valueCapture.capture());

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
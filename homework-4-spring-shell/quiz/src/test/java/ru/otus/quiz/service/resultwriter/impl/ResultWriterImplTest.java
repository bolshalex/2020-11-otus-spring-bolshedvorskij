package ru.otus.quiz.service.resultwriter.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.quiz.domain.io.LocalizedIoService;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.domain.model.QuizResult;
import ru.otus.quiz.service.resultwriter.ResultWriter;

import java.util.List;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
class ResultWriterImplTest {

    @MockBean
    private LocalizedIoService localizedIoService;
    @Autowired
    private ResultWriter resultWriter;

    @Test
    void writeResult() {

        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argsCaptor = ArgumentCaptor.forClass(String.class);
        doNothing().when(localizedIoService).printMessage(valueCapture.capture(), argsCaptor.capture());


        resultWriter.writeResult(new Player("Player1"), getPlayerQuizResult());
        String result = valueCapture.getValue();
        String expectedResult = "test.successful";
        Assertions.assertThat(result).isEqualTo(expectedResult);

        String[] expectedArgs = new String[]{"Player1", "1"};
        List<String> actualArgs = argsCaptor.getAllValues();
        Assertions.assertThat(actualArgs.toArray()).isEqualTo(expectedArgs);
    }

    private QuizResult getPlayerQuizResult() {
        return new QuizResult(1, true);
    }

    @ComponentScan(basePackageClasses = {ResultWriter.class})
    @SpringBootConfiguration
    public static class ResultWriterTestConfiguration {
    }
}
package ru.otus.quiz.domain.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.quiz.domain.LocalizedMessageProvider;

@Component
public class LocalizedIoService {
    private final IoService ioService;
    private final LocalizedMessageProvider localizedMessageProvider;

    @Autowired
    public LocalizedIoService(IoService ioService, LocalizedMessageProvider localizedMessageProvider) {
        this.ioService = ioService;
        this.localizedMessageProvider = localizedMessageProvider;
    }

    public String askNotEmptyString(String messageCode) {
        String message = localizedMessageProvider.getMessage(messageCode);
        String emptyAnswerMessage = localizedMessageProvider.getMessage("empty.answer.try.again");
        return ioService.askNotEmptyString(message, emptyAnswerMessage);
    }

    public int askInteger(String message, AnswerValidator<Integer> validator) {
        String invalidAnswerMessage = localizedMessageProvider.getMessage("invalid.answer.number");
        return ioService.askInteger(message, validator, invalidAnswerMessage);
    }

    public void printMessage(String messageCode, String... args) {
        String message = localizedMessageProvider.getMessage(messageCode, args);
        ioService.print(message);
    }
}

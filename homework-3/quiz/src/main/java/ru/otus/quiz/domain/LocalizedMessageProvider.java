package ru.otus.quiz.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.quiz.config.QuizProperty;

import java.util.Locale;

@Component
public class LocalizedMessageProvider {
    private final Locale locale;
    private final MessageSource messageSource;

    @Autowired
    public LocalizedMessageProvider(MessageSource messageSource, QuizProperty quizProperty) {
        this.messageSource = messageSource;
        locale = quizProperty.getLocale();
    }

    public String getMessage(String messageCode) {
        return messageSource.getMessage(messageCode, null, locale);
    }

    public String getMessage(String messageCode, String... args) {
        return messageSource.getMessage(messageCode, args, locale);
    }

}

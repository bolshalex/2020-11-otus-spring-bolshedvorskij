package ru.otus.quiz.domain.io.ask.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.quiz.domain.LocalizedMessageProvider;
import ru.otus.quiz.domain.io.ask.AnswerValidator;
import ru.otus.quiz.domain.io.ask.Asker;
import ru.otus.quiz.domain.io.out.Printer;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class CmdAsker implements Asker {
    private final Scanner scanner;
    private final Printer printer;
    private final LocalizedMessageProvider messageProvider;

    @Autowired
    public CmdAsker(@Value("#{ T(java.lang.System).in}") InputStream inputStream,
                    Printer printer,
                    LocalizedMessageProvider messageProvider) {
        this.scanner = new Scanner(inputStream);
        this.printer = printer;
        this.messageProvider = messageProvider;
    }

    @Override
    public String askNotEmptyString(String message) {
        String stringAnswer = null;
        do {
            printer.print(message);
            if (scanner.hasNextLine()) {
                stringAnswer = scanner.nextLine();
            }
            stringAnswer = StringUtils.strip(stringAnswer, " \t");
            if (StringUtils.isNotEmpty(stringAnswer)) {
                return stringAnswer;
            }
            printer.print(messageProvider.getMessage("empty.answer.try.again"));
        } while (true);
    }

    @Override
    public int askInteger(String message) {
        printer.print(message);
        return scanner.nextInt();
    }

    @Override
    public int askInteger(String message, AnswerValidator<Integer> validator) {
        Integer answer;
        do {
            answer = askInteger(message);
            if (validator.check(answer)) {
                return answer;
            }
            printer.print(messageProvider.getMessage("invalid.answer.number"));
        } while (true);
    }
}

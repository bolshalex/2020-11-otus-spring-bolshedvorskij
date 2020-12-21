package ru.otus.quiz.domain.io.ask.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.quiz.domain.io.ask.Asker;
import ru.otus.quiz.domain.io.out.Printer;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class CmdAsker implements Asker {
    private final Scanner scanner;
    private final Printer printer;

    @Autowired
    public CmdAsker(@Value("#{ T(java.lang.System).in}") InputStream inputStream, Printer printer) {
        this.scanner = new Scanner(inputStream);
        this.printer = printer;
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
        } while (StringUtils.isEmpty(stringAnswer));
        return stringAnswer;
    }

    @Override
    public int askInteger(String message) {
        printer.print(message);
        return scanner.nextInt();
    }
}

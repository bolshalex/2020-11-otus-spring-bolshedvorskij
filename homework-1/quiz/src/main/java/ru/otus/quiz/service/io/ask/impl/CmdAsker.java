package ru.otus.quiz.service.io.ask.impl;

import org.apache.commons.lang3.StringUtils;
import ru.otus.quiz.service.io.ask.Asker;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CmdAsker implements Asker {
    private final Scanner scanner;
    private final PrintStream out;

    public CmdAsker(InputStream inputStream, OutputStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.out = new PrintStream(outputStream);
    }

    @Override
    public String askNotEmptyString(String message) {
        String stringAnswer = null;
        do {
            out.println(message);
            if (scanner.hasNextLine()) {
                stringAnswer = scanner.nextLine();
            }
            stringAnswer = StringUtils.strip(stringAnswer, " \t");
        } while (StringUtils.isEmpty(stringAnswer));
        return stringAnswer;
    }

    @Override
    public int askInteger(String message) {
        out.println(message);
        return scanner.nextInt();
    }
}

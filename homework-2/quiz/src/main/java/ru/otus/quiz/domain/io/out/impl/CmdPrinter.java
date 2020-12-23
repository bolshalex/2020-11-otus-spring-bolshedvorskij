package ru.otus.quiz.domain.io.out.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.quiz.domain.io.out.Printer;

import java.io.OutputStream;
import java.io.PrintStream;

@Component
public class CmdPrinter implements Printer {
    private final PrintStream out;

    public CmdPrinter(@Value("#{ T(java.lang.System).out}") OutputStream outputStream) {
        this.out = new PrintStream(outputStream);
    }

    @Override
    public void print(String string) {
        out.println(string);
    }
}

package ru.otus.quiz.service.io.out.impl;

import ru.otus.quiz.service.io.out.Printer;

import java.io.OutputStream;
import java.io.PrintStream;

public class CmdPrinter implements Printer {
    private final PrintStream out;

    public CmdPrinter(OutputStream outputStream) {
        this.out = new PrintStream(outputStream);
    }

    @Override
    public void print(String string) {
        out.println(string);
    }
}

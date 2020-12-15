package ru.otus.quiz.service.io.out.impl;

import ru.otus.quiz.service.io.out.Printer;

public class CmdPrinter implements Printer {

    @Override
    public void print(String string) {
        System.out.println(string);
    }
}

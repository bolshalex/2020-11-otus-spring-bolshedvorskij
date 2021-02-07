package ru.otus.quiz.domain.io;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class CmdIoService implements IoService {
    private final Scanner scanner;
    private final PrintStream out;

    @Autowired
    public CmdIoService(@Value("#{ T(java.lang.System).in}") InputStream inputStream,
                        @Value("#{ T(java.lang.System).out}") OutputStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.out = new PrintStream(outputStream);
    }

    @Override
    public String askNotEmptyString(String message, String emptyAnswerMessage) {
        String stringAnswer = null;
        do {
            print(message);
            if (scanner.hasNextLine()) {
                stringAnswer = scanner.nextLine();
            }
            stringAnswer = StringUtils.strip(stringAnswer, " \t");
            if (StringUtils.isNotEmpty(stringAnswer)) {
                return stringAnswer;
            }
            print(emptyAnswerMessage);
        } while (true);
    }

    @Override
    public int askInteger(String message) {
        print(message);
        return scanner.nextInt();
    }

    @Override
    public int askInteger(String message, AnswerValidator<Integer> validator, String invalidAnswerMessage) {
        int answer;
        do {
            answer = askInteger(message);
            if (validator.check(answer)) {
                return answer;
            }
            print(invalidAnswerMessage);
        } while (true);
    }

    @Override
    public void print(String string) {
        out.println(string);
    }
}

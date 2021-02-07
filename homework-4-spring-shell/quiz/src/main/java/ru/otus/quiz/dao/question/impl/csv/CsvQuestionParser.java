package ru.otus.quiz.dao.question.impl.csv;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.otus.quiz.domain.model.Question;

import java.util.ArrayList;
import java.util.List;

@Component
public class CsvQuestionParser {
    private CSVRecord csvRecord;

    public Question parseCsvRecord(CSVRecord csvRecord) {
        this.csvRecord = csvRecord;

        return Question.builder()
                .question(csvRecord.get(CsvHeaders.QUESTION))
                .answerVariants(parseStringList(CsvHeaders.ANSWER_VARIANTS))
                .correctAnswer(parseInt(CsvHeaders.CORRECT_ANSWER))
                .build();
    }

    private List<String> parseStringList(String header) {
        String value = csvRecord.get(header);
        if (StringUtils.isEmpty(value)) {
            return new ArrayList<>();
        }
        List<String> finalValues = new ArrayList<>();

        for (String s : StringUtils.split(value, ";")) {
            String finalValue = clearString(s);
            if (StringUtils.isNotEmpty(finalValue)) {
                finalValues.add(finalValue);
            }
        }
        return finalValues;
    }

    private int parseInt(String header) {
        String rawValues = csvRecord.get(header);
        return Integer.parseInt(rawValues);
    }

    private String clearString(String value) {
        return StringUtils.strip(value, " \t");
    }

}

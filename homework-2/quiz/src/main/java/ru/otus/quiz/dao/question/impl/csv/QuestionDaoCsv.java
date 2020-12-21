package ru.otus.quiz.dao.question.impl.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.otus.quiz.dao.question.QuestionDao;
import ru.otus.quiz.dao.question.exception.QuestionDaoException;
import ru.otus.quiz.domain.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionDaoCsv implements QuestionDao {
    private final String questionFilePath;
    private final CsvQuestionParser csvQuestionParser = new CsvQuestionParser();

    public QuestionDaoCsv(@Value("${quizProperty.questionPath}") String questionFilePath) {
        this.questionFilePath = questionFilePath;
    }

    @NonNull
    public List<Question> getQuestions() throws QuestionDaoException {
        if (StringUtils.isEmpty(questionFilePath)) {
            throw new QuestionDaoException("Path to questions isn't specified");
        }

        List<Question> questions = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(questionFilePath)))) {
            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreEmptyLines()
                    .withTrim();
            CSVParser csvParser = new CSVParser(reader, csvFormat);

            for (CSVRecord csvRecord : csvParser) {
                questions.add(csvQuestionParser.parseCsvRecord(csvRecord));
            }
        } catch (IOException e) {
            throw new QuestionDaoException(e);
        }
        return questions;
    }
}

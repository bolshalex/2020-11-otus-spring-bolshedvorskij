package ru.otus.quiz.dao.question.impl.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.otus.quiz.dao.question.QuestionResourcePathProvider;
import ru.otus.quiz.dao.question.QuestionDao;
import ru.otus.quiz.dao.question.exception.QuestionDaoException;
import ru.otus.quiz.domain.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final QuestionResourcePathProvider questionResourcePathProvider;
    private final CsvQuestionParser csvQuestionParser;

    @Autowired
    public QuestionDaoCsv(QuestionResourcePathProvider questionResourcePathProvider,
                          CsvQuestionParser csvQuestionParser) {
        this.questionResourcePathProvider = questionResourcePathProvider;
        this.csvQuestionParser = csvQuestionParser;
    }

    @NonNull
    public List<Question> getQuestions() throws QuestionDaoException {
        String path = questionResourcePathProvider.getQuestionResourcePath();
        if (StringUtils.isEmpty(path)) {
            throw new QuestionDaoException("Path to questions isn't specified");
        }

        List<Question> questions = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)))) {
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

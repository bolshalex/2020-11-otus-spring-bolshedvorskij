package ru.otus.library;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
    }
}

package ru.otus.quiz;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.quiz.service.application.Application;
import ru.otus.quiz.service.application.exception.ApplicationException;

public class QuizApplication {


    public static void main(String[] args) throws ApplicationException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/ru/otus/quiz/spring/spring-context.xml");

        Application application = (Application) context.getBean("application");
        application.run();
    }
}

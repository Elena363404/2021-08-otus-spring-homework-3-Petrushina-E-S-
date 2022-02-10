package ru.otus.elena363404;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.elena363404.exception.QuestionReadingException;
import ru.otus.elena363404.service.QuestionService;

@SpringBootApplication
public class Quiz {

	public static void main(String[] args) throws QuestionReadingException {

		ApplicationContext ctx = SpringApplication.run(Quiz.class, args);

		QuestionService service = ctx.getBean(QuestionService.class);

		service.testStudent();

	}

}

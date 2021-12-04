package ru.otus.elena363404.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import ru.otus.elena363404.Quiz;
import ru.otus.elena363404.config.AppConfig;
import ru.otus.elena363404.dao.QuestionDao;
import ru.otus.elena363404.domain.Question;
import ru.otus.elena363404.exception.QuestionReadingException;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Класс QuestionService")
class QuestionServiceTest {

  @Autowired
  private QuestionService questionService;

  @Autowired
  private AppConfig config;

  @Autowired
  private MessageSource msg;

  @Autowired
  private QuestionDao dao;

  @Test
  void getMessageRU() {
    Locale langLocale = new Locale("ru_RU");
    String tstQuestion = msg.getMessage("strings.reply.to.input", null, langLocale);
    assertEquals(tstQuestion, "Введите номер ответа: ");
  }

  @Test
  void getMessageEN() {
    Locale langLocale = new Locale("en_EN");
    String tstQuestion = msg.getMessage("strings.reply.to.input", null, langLocale);
    assertEquals(tstQuestion, "Input num of answer: ");
  }

  @Test
  void testStudentFailTest() throws QuestionReadingException {
    int cntRightAnswer = 0;
    int[] arrAnswer = new int[] {1, 2, 1, 3, 1};
    HashMap <String, String> allQuizPath = config.getAllQuizPath();
    List<Question> questionList = dao.getAllQuestions(allQuizPath.get("en"));
    for (int i = 0; i < questionList.size(); i++) {
      int answer = questionList.get(i).getAnswer().getAnswer();
      if (answer == arrAnswer[i]) {
        cntRightAnswer++;
      }
    }
    assertEquals(1, cntRightAnswer);
  }

  @Test
  void testStudentPassTest() throws QuestionReadingException {
    int cntRightAnswer = 0;
    int[] arrAnswer = new int[] {3, 2, 2, 2, 3};
    HashMap <String, String> allQuizPath = config.getAllQuizPath();
    List<Question> questionList = dao.getAllQuestions(allQuizPath.get("ru"));
    for (int i = 0; i < questionList.size(); i++) {
      int answer = questionList.get(i).getAnswer().getAnswer();
      if (answer == arrAnswer[i]) {
        cntRightAnswer++;
      }
    }
    assertEquals(5, cntRightAnswer);
  }



}
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
import ru.otus.elena363404.domain.Question;
import ru.otus.elena363404.exception.QuestionReadingException;
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

  @Test
  void getQuestionList() throws QuestionReadingException {
    assertEquals(5, config.getDao().getAllQuestions().size());
  }

  @Test
  void getCntRightAnswer() {

  }

  @Test
  void getMessageRU() {
    Locale langLocale = new Locale("RU-ru");
    String tstQuestion = msg.getMessage("strings.question.1", null, langLocale);
    assertEquals(tstQuestion, "Что из этого не имеет структуру на основе индекса?");
  }

  @Test
  void testStudentFailTest() throws QuestionReadingException {
    int cntRightAnswer = 0;
    int[] arrAnswer = new int[] {1, 2, 1, 3, 1};
    List<Question> questionList = config.getDao().getAllQuestions();
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
    List<Question> questionList = config.getDao().getAllQuestions();
    for (int i = 0; i < questionList.size(); i++) {
      int answer = questionList.get(i).getAnswer().getAnswer();
      if (answer == arrAnswer[i]) {
        cntRightAnswer++;
      }
    }
    assertEquals(5, cntRightAnswer);
  }



}
package ru.otus.elena363404.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.elena363404.exception.QuestionReadingException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Класс AppConfig")
class AppConfigTest {

  @Autowired
  private AppConfig config;

  @Test
  void getQuizPath() {
    Map<Integer, String> allQuizPath = config.getAllQuizPath();
    assertEquals(allQuizPath.get(2),"fileQuizRu.csv");
    assertEquals(allQuizPath.get(1),"fileQuizEn.csv");
  }

  @Test
  void getCntQuestion() throws QuestionReadingException {
    //assertEquals(5, config.getCntQuestion());
  }

  @Test
  void getCntAnswerToPassTest() {
   // assertEquals(config.getCntAnswerToPassTest(),4);
  }
}
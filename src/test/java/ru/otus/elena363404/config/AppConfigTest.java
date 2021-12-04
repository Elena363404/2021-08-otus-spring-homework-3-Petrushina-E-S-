package ru.otus.elena363404.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.elena363404.exception.QuestionReadingException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Класс AppConfig")
class AppConfigTest {

  @Autowired
  private AppConfig config;

  @Test
  void getQuizPath() {
    HashMap <String, String> allQuizPath = config.getAllQuizPath();
    assertEquals(allQuizPath.get("ru"),"fileQuizRu.csv");
    assertEquals(allQuizPath.get("en"),"fileQuizEn.csv");
  }

  @Test
  void getCntQuestion() throws QuestionReadingException {
    assertEquals(5, config.getCntQuestion());
  }

  @Test
  void getCntAnswerToPassTest() {
    assertEquals(config.getCntAnswerToPassTest(),4);
  }

  @Test
  void getAvailableLangTest() {
    HashMap<Integer, String> availableLang = config.getAvailableLang();
    assertEquals(availableLang.size(),2);
    assertEquals(availableLang.get(1), "English");
    assertEquals(availableLang.get(2), "Russian");
  }
}
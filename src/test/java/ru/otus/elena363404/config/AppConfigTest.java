package ru.otus.elena363404.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Класс AppConfig")
class AppConfigTest {

  @Autowired
  private AppConfig config;

  @Test
  void getQuizPath() {
    assertEquals(config.getQuizPath(),"fileQuiz.csv");
  }

  @Test
  void getCntAnswerToPassTest() {
    assertEquals(config.getCntAnswerToPassTest(),4);
  }
}
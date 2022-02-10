package ru.otus.elena363404.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "features")
@Component
@Primary
public class AppConfig {

  private Map<Integer, String> allQuizPath;
  private int cntAnswerToPassTest;
  private int cntQuestion;


  public Map<Integer, String> getAllQuizPath() {
    return allQuizPath;
  }

  public void setAllQuizPath(Map<Integer, String> quizPath) {
    this.allQuizPath = quizPath;
  }

  public int getCntAnswerToPassTest() {
    return cntAnswerToPassTest;
  }

  public void setCntAnswerToPassTest(int cntAnswerToPassTest) {
    this.cntAnswerToPassTest = cntAnswerToPassTest;
  }

  public int getCntQuestion() {
    return cntQuestion;
  }

  public void setCntQuestion(int cntQuestion) {
    this.cntQuestion = cntQuestion;
  }
}

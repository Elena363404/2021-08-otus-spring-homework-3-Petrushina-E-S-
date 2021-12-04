package ru.otus.elena363404.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.HashMap;

@ConfigurationProperties(prefix = "features")
@Component
public class AppConfig {

  private HashMap<String, String> allQuizPath;
  private int cntAnswerToPassTest;
  private int cntQuestion;

  private HashMap<Integer, String> availableLang;

  public HashMap<Integer, String> getAvailableLang() {
    return availableLang;
  }

  public void setAvailableLang(HashMap<Integer, String> availableLang) {
    this.availableLang = availableLang;
  }

  public HashMap<String, String> getAllQuizPath() {
    return allQuizPath;
  }

  public void setAllQuizPath(HashMap<String, String> quizPath) {
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

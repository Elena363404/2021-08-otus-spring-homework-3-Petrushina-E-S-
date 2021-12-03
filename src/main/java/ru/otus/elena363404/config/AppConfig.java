package ru.otus.elena363404.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.elena363404.dao.QuestionDaoCsv;
import ru.otus.elena363404.service.IOService;

@ConfigurationProperties(prefix = "features")
@RequiredArgsConstructor
@Component
public class AppConfig {

  private String quizPath;

  private int cntAnswerToPassTest;

  private QuestionDaoCsv dao;

  private final IOService ioService;

  private final MessageSource messageSource;

  public QuestionDaoCsv getDao() {

    if (dao == null) {
      dao = new QuestionDaoCsv(quizPath);
    }
    return dao;
  }

  public String getQuizPath() {
    return quizPath;
  }

  public void setQuizPath(String quizPath) {
    this.quizPath = quizPath;
  }

  public int getCntAnswerToPassTest() {

    return cntAnswerToPassTest;
  }

  public void setCntAnswerToPassTest(int cntAnswerToPassTest) {
    this.cntAnswerToPassTest = cntAnswerToPassTest;
  }

  public IOService getIOStreamService() {
    return ioService;
  }

  public MessageSource getMessageSource() {
    return messageSource;
  }


}

package ru.otus.elena363404.service;

import org.springframework.stereotype.Service;
import ru.otus.elena363404.config.LangConfig;
import ru.otus.elena363404.dao.QuestionDao;
import ru.otus.elena363404.domain.Option;
import ru.otus.elena363404.domain.Question;
import ru.otus.elena363404.exception.QuestionReadingException;

import java.util.*;
@Service
public class QuestionService {

  private final QuestionDao dao;
  private final IOService ioService;
  private final LangConfig config;
  private final MessageService messageService;

  private QuestionService(LangConfig config, QuestionDao dao, IOService ioService, MessageService messageService) {
    this.config = config;
    this.ioService = ioService;
    this.dao = dao;
    this.messageService = messageService;
  }

  public void testStudent() throws QuestionReadingException {

    int numLang = getLang();
    config.setLangNum(numLang);

    int cntTestRightAnswer = getCntRightAnswer();
    String resultTest = getResultTest(cntTestRightAnswer);

    printResultTest(cntTestRightAnswer, resultTest);

  }

  private int getCntRightAnswer() throws QuestionReadingException {
    List<Question> questionList = getQuestionList();
    int cntRightAnswer = 0;

    for (int i = 0; i < questionList.size(); i++) {
      String question = questionList.get(i).getQuestion();
      int answer = questionList.get(i).getAnswer().getAnswer();
      List<Option> optionList = questionList.get(i).getOptions();

      String options = getOptions(optionList);

      ioService.out("\n" + question);
      ioService.out(options);
      ioService.out(messageService.getLocalMessage("strings.reply.to.input"));

      int inAnswer = ioService.readInt();

      if (answer == inAnswer) {
        cntRightAnswer = cntRightAnswer + 1;
      }
    }
    return cntRightAnswer;
  }

  private List<Question> getQuestionList() throws QuestionReadingException {
    List<Question> questionList = dao.getAllQuestions();
    return questionList;
  }

  private String getOptions(List<Option> optionList) {
    StringBuilder options = new StringBuilder();
    for (int j = 0; j < optionList.size(); j++) {
      Option option = optionList.get(j);
      options = options.append(option.getId()).append(".").append(option.getOption()).append("\n");
    }
    return options.toString();
  }

  private int getLang() {
    int inNumLang = 0;

    ioService.out("Choose language: \n" + config.getAllLangStr());
    ioService.out("Input num of answer: ");
    String input = ioService.readString();

    if (input.matches("\\d+")) {
      inNumLang = Integer.parseInt(input);
      if (inNumLang > config.getCntAvailableLang() || inNumLang < 0) {
        inNumLang = 0;
      }
    }

    while (inNumLang == 0) {
      ioService.out("You have selected the wrong language!");
      inNumLang = getLang();
    }
    return inNumLang;
  }

  private String getResultTest(int cntTestRightAnswer) {
    String resultTest = cntTestRightAnswer + "/" + config.getCntQuestion();
    return resultTest;
  }

  private void printResultTest (int cntTestRightAnswer, String resultTest) {

    if (cntTestRightAnswer >= config.getCntAnswerToPassTest()) {
      ioService.out(messageService.getLocalMessage("strings.reply.pass.test") + resultTest);
    } else {
      ioService.out(messageService.getLocalMessage("strings.reply.fail.test") + resultTest);
    }
  }


}

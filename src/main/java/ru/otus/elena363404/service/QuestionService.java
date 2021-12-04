package ru.otus.elena363404.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.config.AppConfig;
import ru.otus.elena363404.dao.QuestionDao;
import ru.otus.elena363404.domain.Option;
import ru.otus.elena363404.domain.Question;
import ru.otus.elena363404.exception.QuestionReadingException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class QuestionService {

  private final int cntAnswerForPassTest;
  private final int cntQuestion;
  private final QuestionDao dao;
  private final IOService ioService;
  private final MessageSource messageSource;
  private final HashMap<Integer, String> availableLang;
  private final HashMap<String, String> allQuizPath;

  public QuestionService(AppConfig config, QuestionDao dao, MessageSource messageSource, IOStreamService ioService) {
    this.cntAnswerForPassTest = config.getCntAnswerToPassTest();
    this.cntQuestion = config.getCntQuestion();
    this.availableLang = config.getAvailableLang();
    this.allQuizPath = config.getAllQuizPath();
    this.ioService = ioService;
    this.dao = dao;
    this.messageSource = messageSource;
  }


  public void testStudent() throws QuestionReadingException {

    String lang = getLang();
    String nameQuizPath = allQuizPath.get(lang.toLowerCase());
    lang = (lang.toLowerCase() + "-" + lang.toUpperCase());
    Locale langLocale = Locale.forLanguageTag(lang);
    int cntTestRightAnswer = getCntRightAnswer(langLocale, nameQuizPath);
    String resultTest = getResultTest(cntTestRightAnswer);

    printResultTest(cntTestRightAnswer, resultTest, langLocale);

  }

  private int getCntRightAnswer(Locale langLocale, String nameQuizPath) throws QuestionReadingException {
    List<Question> questionList = getQuestionList(nameQuizPath);
    int cntRightAnswer = 0;

    for (int i = 0; i < questionList.size(); i++) {
      String question = questionList.get(i).getQuestion();
      int answer = questionList.get(i).getAnswer().getAnswer();
      List<Option> optionList = questionList.get(i).getOptions();

      String options = getOptions(optionList);

      ioService.out("\n" + question);
      ioService.out(options);
      ioService.out(getLocalMessage("strings.reply.to.input", langLocale));

      int inAnswer = ioService.readInt();

      if (answer == inAnswer) {
        cntRightAnswer = cntRightAnswer + 1;
      }
    }
    return cntRightAnswer;
  }

  private List<Question> getQuestionList(String quizPath) throws QuestionReadingException {
    List<Question> questionList = dao.getAllQuestions(quizPath);
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

  private String getLang() {
    String lang = null;
    int inNumLang;
    String allLang = convertHashmapToStr(availableLang);

    ioService.out("Choose language: \n" + allLang);
    ioService.out("Input num of answer: ");
    String input = ioService.readString();

    if (input.matches("\\d+")) {
      inNumLang = Integer.parseInt(input);
      if (inNumLang <= availableLang.size() && inNumLang > 0) {
        lang = availableLang.get(inNumLang);
        lang = (lang != null && lang.length() > 1) ? lang.substring(0, 2) : lang;
      }
    }

    lang = lang == null ? "ERR" : lang;

    while (lang.equals("ERR")) {
      ioService.out("You have selected the wrong language!");
      lang = getLang();
    }
    return lang;
  }

  private String getLocalMessage(String txtMsg, Locale locale) {
    txtMsg = messageSource.getMessage(txtMsg, null, locale);

    return txtMsg;
  }

  private String getResultTest(int cntTestRightAnswer) {
    String resultTest = cntTestRightAnswer + "/" + cntQuestion;
    return resultTest;
  }

  private void printResultTest (int cntTestRightAnswer, String resultTest, Locale langLocale) {

    if (cntTestRightAnswer >= cntAnswerForPassTest) {
      ioService.out(getLocalMessage("strings.reply.pass.test", langLocale) + resultTest);
    } else {
      ioService.out(getLocalMessage("strings.reply.fail.test",langLocale) + resultTest);
    }
  }

  private String convertHashmapToStr(Map hm) {
    AtomicInteger cnt = new AtomicInteger(1);
    String mapAsStr = (String) hm.keySet().stream()
      .map(key -> cnt.getAndIncrement() + "." + hm.get(key))
      .collect(Collectors.joining("\n"));

    return mapAsStr;
  }

}

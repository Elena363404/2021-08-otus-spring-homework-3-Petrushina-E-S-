package ru.otus.elena363404.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.config.AppConfig;
import ru.otus.elena363404.dao.QuestionDao;
import ru.otus.elena363404.domain.Option;
import ru.otus.elena363404.domain.Question;
import ru.otus.elena363404.exception.QuestionReadingException;

import java.util.List;
import java.util.Locale;

@Service
public class QuestionService {

  private final int cntAnswerForPassTest;
  private final QuestionDao dao;
  private final IOService ioService;
  private final MessageSource messageSource;

  public QuestionService(AppConfig config) {
    this.dao = config.getDao();
    this.cntAnswerForPassTest = config.getCntAnswerToPassTest();
    this.ioService = config.getIOStreamService();
    this.messageSource = config.getMessageSource();
  }


  public void testStudent() throws QuestionReadingException {

    String lang = getLang();
    Locale langLocale = Locale.forLanguageTag(lang);
    int questionListSize = getQuestionList().size();
    int cntTestRightAnswer = getCntRightAnswer(langLocale);
    String resultTest = getResultTest(cntTestRightAnswer, questionListSize);

    printResultTest(cntTestRightAnswer, resultTest, langLocale);

  }

  private int getCntRightAnswer(Locale langLocale) throws QuestionReadingException {
    List<Question> questionList = getQuestionList();
    int cntRightAnswer = 0;

    for (int i = 0; i < questionList.size(); i++) {
      String question = "strings.question." + (i+1);
      int answer = questionList.get(i).getAnswer().getAnswer();
      List<Option> optionList = questionList.get(i).getOptions();

      String options = getOptions(optionList);

      ioService.out(getLocalMessage(question, langLocale));
      ioService.out(options);
      ioService.out(getLocalMessage("strings.reply.to.input", langLocale));

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

  private String getLang() {
    String lang;
    int inNumLang = 0;

    ioService.out("Choose language: \n1.English \n2.Russian");
    ioService.out("Input num of answer: ");
    String input = ioService.readString();

    if (input.matches("\\d+")) {
      inNumLang = Integer.parseInt(input);
    }
    lang = inNumLang == 1 ? "en-EN" : (inNumLang == 2 ? "ru-RU" : "ERR");

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

  private String getResultTest(int cntTestRightAnswer, int questionListSize) {
    String resultTest = cntTestRightAnswer + "/" + questionListSize;
    return resultTest;
  }

  private void printResultTest (int cntTestRightAnswer, String resultTest, Locale langLocale) {

    if (cntTestRightAnswer >= cntAnswerForPassTest) {
      ioService.out(getLocalMessage("strings.reply.pass.test", langLocale) + resultTest);
    } else {
      ioService.out(getLocalMessage("strings.reply.fail.test",langLocale) + resultTest);
    }
  }

}

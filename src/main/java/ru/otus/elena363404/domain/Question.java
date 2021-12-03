package ru.otus.elena363404.domain;

import java.util.List;

public class Question {

  private Integer num;
  private String question;
  private List<Option> options;
  private Answer answer;

  public Question(Integer num, String question, List<Option> options, Answer answer) {
    this.num = num;
    this.question = question;
    this.options = options;
    this.answer = answer;
  }

  @Override
  public String toString() {
    return "Question{" +
      "num=" + num +
      ", question='" + question + '\'' +
      ", options=" + options +
      ", answer=" + answer + '}';
  }

  public Answer getAnswer() {
    return answer;
  }

  public Integer getNum() {
    return num;
  }

  public List<Option> getOptions() {
    return options;
  }

  public String getQuestion() {
    return question;
  }
}

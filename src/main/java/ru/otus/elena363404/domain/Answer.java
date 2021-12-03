package ru.otus.elena363404.domain;

public class Answer {

  private int num;
  private int answer;

  public Answer(int num, int answer) {
    this.num = num;
    this.answer = answer;
  }

  @Override
  public String toString() {
    return "Answer{" +
      "num=" + num +
      ", answer=" + answer + "}";
  }

  public int getAnswer() {
    return answer;
  }

  public int getNum() {
    return num;
  }
}

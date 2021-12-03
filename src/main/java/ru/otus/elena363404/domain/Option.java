package ru.otus.elena363404.domain;

public class Option {

  private int id;
  private int num;
  private String option;

  public Option(int id, int num, String option) {
    this.id = id;
    this.num = num;
    this.option = option;
  }

  @Override
  public String toString() {
    return "Option{" +
      "id=" + id +
      ", num=" + num +
      ", option='" + option + '\'' +
      '}';
  }

  public String getOption() {
    return option;
  }

  public int getNum() {
    return num;
  }

  public int getId() {
    return id;
  }
}

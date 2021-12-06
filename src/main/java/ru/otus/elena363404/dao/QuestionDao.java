package ru.otus.elena363404.dao;

import ru.otus.elena363404.domain.Question;
import ru.otus.elena363404.exception.QuestionReadingException;

import java.util.List;

public interface QuestionDao {

  List<Question> getAllQuestions() throws QuestionReadingException;
}

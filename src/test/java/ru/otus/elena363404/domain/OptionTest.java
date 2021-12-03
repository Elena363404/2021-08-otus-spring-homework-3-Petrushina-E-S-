package ru.otus.elena363404.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Класс Option")
class OptionTest {

  @DisplayName("корректно создается конструктором")
  @Test
  void shouldHaveCorrectConstructor() {
    Option option = new Option(1, 2, "Option1/Option2/Option3");

    assertEquals("Option1/Option2/Option3", option.getOption());
    assertEquals(1, 2, option.getNum());
  }

}
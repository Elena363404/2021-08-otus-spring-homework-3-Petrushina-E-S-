package ru.otus.elena363404.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.elena363404.service.MessageService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Класс LangConfig")
class LangConfigTest {

  @Autowired
  private LangConfig config;

  @Autowired
  private MessageService messageService;

  @Test
  void getAllLangStr() {
    assertEquals(config.getAllLangStr(), "1.English\n2.Russian");
  }


  @Test
  void getCntAvailableLang() {
    assertEquals(config.getAvailableLang().size(), 2);
  }

  @Test
  void getLocalFileName() {
    config.setLangNum(2);
    assertEquals(config.getLocalFileName(), "fileQuizRu.csv");
  }

  @Test
  void getLocalMessage() {
    config.setLangNum(2);
    assertEquals(messageService.getLocalMessage("strings.reply.to.input"), "Введите номер ответа: ");
    config.setLangNum(1);
    assertEquals(messageService.getLocalMessage("strings.reply.to.input"), "Input num of answer: ");
  }

  @Test
  void getAvailableLang() {
    Map<Integer, String> hm = config.getAvailableLang();

    assertEquals(hm.get(1), "English");
    assertEquals(hm.get(2), "Russian");
  }

  @Test
  void getLangNum() {
    config.setLangNum(2);
    assertEquals(config.getLangNum(), 2);
    config.setLangNum(1);
    assertEquals(config.getLangNum(), 1);
  }

  @Test
  void getAllLocale() {
    Map<Integer, String> hm = config.getAllLocale();

    assertEquals(hm.get(1), "en-EN");
    assertEquals(hm.get(2), "ru-RU");
  }
}
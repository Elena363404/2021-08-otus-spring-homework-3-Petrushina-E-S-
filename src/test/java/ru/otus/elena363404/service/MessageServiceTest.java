package ru.otus.elena363404.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.elena363404.config.LangConfig;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@DisplayName("Класс MessageService")
class MessageServiceTest {

  @MockBean
  private LangConfig config;

  @Autowired
  private MessageService messageService;

  @Test
  void getLocalMessageTest() {
    when(config.getAllLocale()).thenReturn(getAllLocale());
    when(config.getLangNum()).thenReturn(2);

    assertEquals(messageService.getLocalMessage("strings.reply.to.input"), "Введите номер ответа: ");

  }

  private Map<Integer, String> getAllLocale() {
    Map<Integer, String> hm = new HashMap<>();

    hm.put(1, "en-EN");
    hm.put(2, "ru-RU");
    return hm;
  }


}
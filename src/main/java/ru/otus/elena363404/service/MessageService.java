package ru.otus.elena363404.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.config.LangConfig;

import java.util.Locale;

@Service
public class MessageService {

  private final MessageSource messageSource;
  private final LangConfig config;

  private MessageService (LangConfig config, MessageSource messageSource) {
    this.messageSource = messageSource;
    this.config = config;
  }

  public String getLocalMessage(String txtMsg) {
    txtMsg = messageSource.getMessage(txtMsg, null, Locale.forLanguageTag(config.getAllLocale().get(config.getLangNum())));
    return txtMsg;
  }


}

package ru.otus.elena363404.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "features")
@RequiredArgsConstructor
@Component
public class LangConfig extends AppConfig {

  private String localFileName;
  private String allLangStr;
  private int langNum;
  private int cntAvailableLang;

  private HashMap<Integer, String> allLocale;
  private HashMap<Integer, String> availableLang;
  public Locale langLocale;
  private final MessageSource messageSource;

  public String getAllLangStr() {
    allLangStr = convertHashmapToStr(getAvailableLang());
    return allLangStr;
  }

  public int getCntAvailableLang() {
    return availableLang.size();
  }
  private void setCntAvailableLang(int cntAvailableLang) {
    this.cntAvailableLang = cntAvailableLang;
  }

  public String getLocalFileName() {
    localFileName = getAllQuizPath().get(langNum);
    return localFileName;
  }

  private String convertHashmapToStr(Map hm) {
    AtomicInteger cnt = new AtomicInteger(1);
    String mapAsStr = (String) hm.keySet().stream()
      .map(key -> cnt.getAndIncrement() + "." + hm.get(key))
      .collect(Collectors.joining("\n"));

    return mapAsStr;
  }

  public String getLocalMessage(String txtMsg) {
    txtMsg = messageSource.getMessage(txtMsg, null, langLocale);

    return txtMsg;
  }

  public HashMap<Integer, String> getAvailableLang() {
    return availableLang;
  }

  public void setAvailableLang(HashMap<Integer, String> availableLang) {
    this.availableLang = availableLang;
  }

  public int getLangNum() {
    return langNum;
  }

  public void setLangNum(int langNum) {
    this.langNum = langNum;
    this.langLocale = Locale.forLanguageTag(allLocale.get(langNum));
  }

  public HashMap<Integer, String> getAllLocale() {
    return allLocale;
  }

  public void setAllLocale(HashMap<Integer, String> allLocale) {
    this.allLocale = allLocale;
  }

}

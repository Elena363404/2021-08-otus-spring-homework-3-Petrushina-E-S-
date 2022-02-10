package ru.otus.elena363404.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "features")
@RequiredArgsConstructor
@Component
public class LangConfig extends AppConfig implements FileNameProvider {

  private String localFileName;
  private String allLangStr;
  private int cntAvailableLang;
  private int langNum;

  private Map<Integer, String> allLocale;
  private Map<Integer, String> availableLang;

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

  public int getLangNum() {
    return langNum;
  }

  public void setLangNum(int langNum) {
    this.langNum = langNum;
  }

  private String convertHashmapToStr(Map<Integer, String> hm) {
    AtomicInteger cnt = new AtomicInteger(1);
    String mapAsStr = (String) hm.keySet().stream()
      .map(key -> cnt.getAndIncrement() + "." + hm.get(key))
      .collect(Collectors.joining("\n"));

    return mapAsStr;
  }

  public Map<Integer, String> getAvailableLang() {
    return availableLang;
  }

  public void setAvailableLang(Map<Integer, String> availableLang) {
    this.availableLang = availableLang;
  }

  public Map<Integer, String> getAllLocale() {
    return allLocale;
  }

  public void setAllLocale(Map<Integer, String> allLocale) {
    this.allLocale = allLocale;
  }

}

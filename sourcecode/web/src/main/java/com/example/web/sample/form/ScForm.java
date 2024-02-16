package com.example.web.sample.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public class ScForm implements Serializable {

  //TODO:数字じゃない文字列の時、バリテーションエラー出ない
  //TODO:相関バリテーションのエラーメッセージが出ない
  //平成31秋、令和元年春、令和2年春がない

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  private String genngou;

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  private String year;

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  private String season;

  public String getGenngou() {
    return genngou;
  }

  public void setGenngou(String genngou) {
    this.genngou = genngou;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }

  //TODO:冗長？

  // 相関バリテーション(令和)
  @AssertTrue(message = "開催されていません。")
  private boolean isValidReiwa() {
    //令和である
    if ("令和".equals(genngou)) {
      int yearValue = Integer.parseInt(year);
      //1~5である
      if (yearValue >= 1 && yearValue <= 5) {
        //1,2年春はない
        if (yearValue == 1 && "春期".equals(season) || yearValue == 2 && "春期".equals(season)) {
          return false;
        }
        return true;
      }
      return false;
    }
    return true;
  }
  


  // 相関バリテーション(平成)
  @AssertTrue(message = "開催されていません。")
  private boolean isValidHeisei() {
    //平成である
    if ("平成".equals(genngou)) {
      int yearValue = Integer.parseInt(year);
      //21~31である
      if (yearValue >= 21 && yearValue <= 31) {
        //31年秋はない
        if (yearValue==31 && "秋期".equals(season)) {
          return false;
        }
        return true;
      }
      return false;
    }
    return true;
  }
}

package com.example.web.sample.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import nablarch.core.validation.ee.NumberRange;

import java.io.Serializable;

public class ScForm implements Serializable {

  //平成31秋、令和元年春、令和2年春がない
  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  private String genngou;

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = 1, max = 31, message = "有効な数字を入れてください。")
  private Integer year;

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  private String season;

  public String getGenngou() {
    return genngou;
  }

  public void setGenngou(String genngou) {
    this.genngou = genngou;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }


  // 相関バリテーション(令和)
  @AssertTrue(message = "開催されていません。")
  public boolean isValidReiwa() {
    //値がnullかどうかの確認
    if (genngou == null || year == null || season == null) {             
      return true;                                                        
    }
    //令和でない時
    if (!"令和".equals(genngou)) {
      return true;
    }
    //~0年と6年~はない
    if (year < 1 || year > 5) {
      return false;
    }
    //令和1年春と令和2年春はない
    if (year == 1 && "春期".equals(season) || year == 2 && "春期".equals(season)) {
      return false;
    }
    return true;
  }

  // 相関バリテーション(平成)
  @AssertTrue(message = "開催されていません。")
  public boolean isValidHeisei() {
    //値がnullかどうかの確認
    if (genngou == null || year == null || season == null) {            
      return true;                                                          
    }
    //平成でない時
    if (!"平成".equals(genngou)) {
      return true;
    }
    //~20年と32年~はない    
    if (year < 21 || year > 31) {
      return false;
    }
    //平成31年秋がない
    if (year == 31 && "秋期".equals(season)) {
      return false;
    }
    
    return true;
  }
}

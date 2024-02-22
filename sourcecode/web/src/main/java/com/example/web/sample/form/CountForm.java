package com.example.web.sample.form;

import jakarta.validation.constraints.NotNull;
import nablarch.core.validation.ee.NumberRange;

import java.io.Serializable;

public class CountForm implements Serializable {

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  private String text;

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = 1, max = 999999999)
  private String wordLimit;

  public CountForm() {
    this.wordLimit = "400" ;
    }

  public CountForm(String wordLimit) {
    this.wordLimit = wordLimit;
  }

  public String getText() {
    return text;
  }

  public String getWordLimit() {
    return wordLimit;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setWordLimit(String wordLimit) {
    this.wordLimit = wordLimit;
  }

}

package com.example.web.sample.form;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public class CountForm implements Serializable {

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  private String text;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}

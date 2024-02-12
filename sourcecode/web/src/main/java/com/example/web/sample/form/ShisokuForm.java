package com.example.web.sample.form;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import nablarch.core.validation.ee.NumberRange;

public class ShisokuForm implements Serializable {

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = -999999999, max = 999999999)
  private String number1;

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = -999999999, max = 999999999)
  private String number2;

  public String getNumber1() {
    return number1;
  }

  public void setNumber1(String number1) {
    this.number1 = number1;
  }

  public String getNumber2() {
    return number2;
  }

  public void setNumber2(String number2) {
    this.number2 = number2;
  }
}

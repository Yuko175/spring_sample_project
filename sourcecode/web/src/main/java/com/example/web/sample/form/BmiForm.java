package com.example.web.sample.form;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import nablarch.core.validation.ee.NumberRange;

public class BmiForm implements Serializable {

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = 1, max = 999999999)
  private String height;

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = 1, max = 999999999)
  private String weight;

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }
}

package com.example.web.sample.form;

import jakarta.validation.constraints.NotNull;
import nablarch.core.validation.ee.NumberRange;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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


}

package com.example.web.sample.form;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import nablarch.core.validation.ee.NumberRange;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShisokuForm implements Serializable {

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = -999999999, max = 999999999)
  private String number1;

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = -999999999, max = 999999999)
  private String number2;

}

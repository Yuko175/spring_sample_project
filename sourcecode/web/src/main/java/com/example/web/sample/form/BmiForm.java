package com.example.web.sample.form;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import nablarch.core.validation.ee.NumberRange;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BmiForm implements Serializable {

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = 1, max = 999999999)
  private String height;

  @NotNull(message = "{jakarta.validation.constraints.NotNull}")
  @NumberRange(min = 1, max = 999999999)
  private String weight;

}

package com.example.web.sample.service;

import com.example.web.sample.dto.BmiDto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class BmiService {

  public BmiDto calcBmi(String height, String weight) {
    //BigDecimalに変換
    //BigDecimalに変換する時に、引数は明示的にString型にする必要がある
    BigDecimal calcHeight = new BigDecimal(height);
    BigDecimal calcWeight = new BigDecimal(weight);

    //計算
    BigDecimal divisor = calcHeight.divide(new BigDecimal("100")).pow(2);
    BigDecimal adequateWeight = divisor
      .multiply(new BigDecimal("22"))
      .setScale(2, RoundingMode.HALF_UP);
    BigDecimal bmi = calcWeight.divide(divisor, 2, RoundingMode.HALF_UP);
    BigDecimal comparison = calcWeight.subtract(adequateWeight);

    //体裁整える
    String adequateWeightStr = adequateWeight
      .stripTrailingZeros()
      .toPlainString();
    String comparisonStr = comparison.stripTrailingZeros().toPlainString();
    String bmiStr = bmi.stripTrailingZeros().toPlainString();

    return new BmiDto(adequateWeightStr, comparisonStr, bmiStr);
  }
}

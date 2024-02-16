package com.example.web.sample.service;

import com.example.web.sample.dto.ShisokuDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class ShisokuService {
    public ShisokuDto calcShisoku(String number1, String number2) {
    

 // BigDecimalに変換
    BigDecimal calcNumber1 = new BigDecimal(number1);
    BigDecimal calcNumber2 = new BigDecimal(number2);


    // 計算
    BigDecimal wa = calcNumber1.add(calcNumber2);
    BigDecimal sa = calcNumber1.subtract(calcNumber2);
    BigDecimal seki = calcNumber1.multiply(calcNumber2);
    String shouDto;

    // number1またはnumber2が0の時、割り算の計算は "N/A" 表記にする
    if (calcNumber1.compareTo(BigDecimal.ZERO) == 0 ||
            calcNumber1.compareTo(BigDecimal.ZERO) == 0) {
            shouDto = "N/A";
    } else {
      // 計算
        BigDecimal shou = calcNumber1.divide(calcNumber2, 10, RoundingMode.DOWN);
        // 計算結果を定義に詰める(余分な0は省き、指数表記にならないようにする)
        shouDto = shou.stripTrailingZeros().toPlainString();
    }

    String waDto = wa.stripTrailingZeros().toPlainString();
    String saDto = sa.stripTrailingZeros().toPlainString();
    String sekiDto = seki.stripTrailingZeros().toPlainString();



    return new ShisokuDto(waDto, saDto, sekiDto, shouDto);
    }
}

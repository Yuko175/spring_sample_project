package com.example.web.sample.controller;

import com.example.web.common.errorhandling.OnRejectError;
import com.example.web.sample.form.ShisokuForm;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//TODO：service(dto)の書き分け
@Controller
@RequestMapping("sample/shisoku")
public class ShisokuController {

  @GetMapping
  public String shisoku(Model model) {
    ShisokuForm shisokuForm = new ShisokuForm();

    // 最初に訪れた時のみ、nullにし、それを許容
    if (!model.containsAttribute("shisokuForm")) {
      shisokuForm.setNumber1(null);
      shisokuForm.setNumber2(null);
    }
    model.addAttribute("shisokuForm", shisokuForm);
    return "sample/shisoku";
  }

  @PostMapping
  public String shisokuClear(Model model) {
    ShisokuForm shisokuForm = new ShisokuForm();

    // 最初に訪れた時のみ、nullにし、それを許容
    if (!model.containsAttribute("shisokuForm")) {
      shisokuForm.setNumber1(null);
      shisokuForm.setNumber2(null);
    }
    model.addAttribute("shisokuForm", shisokuForm);
    return "sample/shisoku";
  }

  @PostMapping("/ans")
  @OnRejectError(path = "sample/shisoku")
  public String confirm(
    @Validated ShisokuForm form,
    BindingResult bindingResult,
    Model model
  ) {
    //BigDecimalに変換
    BigDecimal number1 = new BigDecimal(form.getNumber1().toString());
    BigDecimal number2 = new BigDecimal(form.getNumber2().toString());

    //計算
    BigDecimal wa = number1.add(number2);
    BigDecimal sa = number1.subtract(number2);
    BigDecimal seki = number1.multiply(number2);
    //number1またはnumber2が0の時、割り算の計算は "N/A" 表記にする
    if (
      number1.compareTo(BigDecimal.ZERO) == 0 ||
      number2.compareTo(BigDecimal.ZERO) == 0
    ) {
      model.addAttribute("shou", "N/A");
    } else {
      //計算
      BigDecimal shou = number1.divide(number2, 10, RoundingMode.DOWN);
      //計算結果を定義に詰める(余分な0は省き、指数表記にならないようにする)
      model.addAttribute("shou", shou.stripTrailingZeros().toPlainString());
    }

    model.addAttribute("wa", wa.stripTrailingZeros().toPlainString());
    model.addAttribute("sa", sa.stripTrailingZeros().toPlainString());
    model.addAttribute("seki", seki.stripTrailingZeros().toPlainString());

    //print
    System.out.println("number1:" + number1);
    System.out.println("number2:" + number2);

    return "sample/shisoku";
  }
}

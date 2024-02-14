package com.example.web.sample.controller;

import com.example.web.common.errorhandling.OnRejectError;
import com.example.web.sample.form.BmiForm;
import com.example.web.sample.service.BmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//  　人=html
//   　form箱
//   　　 ↓
//   Controller
//   　　 ↑↓
//   Dto箱 Dto箱
//   　　 ↑↓
//   　Service

@Controller
@RequestMapping("sample/bmi")
public class BmiController {

  @Autowired
  private BmiService bmiService;

  @GetMapping
  public String bmi(Model model) {
    BmiForm bmiForm = new BmiForm();

    // 最初に訪れた時のみ、nullにし、それを許容
    if (!model.containsAttribute("bmiForm")) {
      bmiForm.setHeight(null);
      bmiForm.setWeight(null);
    }
    model.addAttribute("bmiForm", bmiForm);
    return "sample/bmi";
  }

  @PostMapping(path = "", params = "clear")
  public String bmiClear(Model model) {
    BmiForm bmiForm = new BmiForm();

    // 最初に訪れた時のみ、nullにし、それを許容
    if (!model.containsAttribute("bmiForm")) {
      bmiForm.setHeight(null);
      bmiForm.setWeight(null);
    }
    model.addAttribute("bmiForm", bmiForm);
    return "sample/bmi";
  }

  @PostMapping(path = "", params = "calc")
  @OnRejectError(path = "sample/bmi")
  public String confirm(
      @Validated BmiForm form,
      BindingResult bindingResult,
      Model model) {
    model.addAttribute(
        "bmiResult",
        bmiService.calcBmi(form.getHeight(), form.getWeight()));

    return "sample/bmi";
  }
}

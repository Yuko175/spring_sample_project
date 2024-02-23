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
    model.addAttribute("bmiForm", new BmiForm());
    return "sample/bmi";
  }

  @PostMapping(path = "", params = "clear")
  public String bmiClear(Model model) {
    model.addAttribute("bmiForm", new BmiForm());
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

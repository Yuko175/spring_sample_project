package com.example.web.sample.controller;

import com.example.web.common.errorhandling.OnRejectError;
import com.example.web.sample.form.ShisokuForm;
import com.example.web.sample.service.BmiService;
import com.example.web.sample.service.ShisokuService;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sample/shisoku")
public class ShisokuController {

  @Autowired
  private ShisokuService shisokuService;

  @GetMapping
  public String shisoku(Model model) {
    model.addAttribute("shisokuForm", new ShisokuForm());
    return "sample/shisoku";
  }

  @PostMapping(path = "",params ="clear")
  public String shisokuClear(Model model) {
    model.addAttribute("shisokuForm", new ShisokuForm());
    return "sample/shisoku";
  }

  @PostMapping(path = "",params ="calc")
  @OnRejectError(path = "sample/shisoku")
  public String confirm(
      @Validated ShisokuForm form,
      BindingResult bindingResult,
      Model model) {
      model.addAttribute(
      "shisokuResult",
        shisokuService.calcShisoku(form.getNumber1(), form.getNumber2()));
  
    return "sample/shisoku";
  
  }
}

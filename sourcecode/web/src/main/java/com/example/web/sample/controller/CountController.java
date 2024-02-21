package com.example.web.sample.controller;

import com.example.web.common.errorhandling.OnRejectError;
import com.example.web.sample.dto.CountDto;
import com.example.web.sample.form.CountForm;
import com.example.web.sample.service.CountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sample/count")
public class CountController {

  @Autowired
  private CountService countService;

  @GetMapping
  public String count(Model model) {
    model.addAttribute("countForm", new CountForm());
    model.addAttribute("countResult", new CountDto());
    return "sample/count";
  }

  @PostMapping(path = "",params ="clear")
  public String countClear(Model model) {
    model.addAttribute("countForm", new CountForm());
    model.addAttribute("countResult", new CountDto());
    return "sample/count";
  }

  @PostMapping(path = "",params ="calc")
  @OnRejectError(path = "sample/count",handlingValidationError = false)
  public String confirm(
      @Validated CountForm form,
      BindingResult bindingResult,
      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("countResult", new CountDto()); 
            return "sample/count"; 
        }
      model.addAttribute(
      "countResult",
        countService.calcCount(form.getText()));
  
    return "sample/count";
  
  }
}

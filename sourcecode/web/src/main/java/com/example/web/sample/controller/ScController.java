package com.example.web.sample.controller;

import com.example.web.common.errorhandling.OnRejectError;
import com.example.web.sample.dto.CountDto;
import com.example.web.sample.dto.ScDateDto;
import com.example.web.sample.form.ScForm;
import com.example.web.sample.service.ScService;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sample/sc")
public class ScController {

  @Autowired
  private ScService scService;

  @GetMapping
  public String sc(Model model) {
    model.addAttribute("scForm", new ScForm());
    model.addAttribute("calcDate",scService.calcDate());
    return "sample/sc";
  }

  @PostMapping(path = "",params ="clear")
  public String scClear(Model model) {
    model.addAttribute("scForm", new ScForm());
    model.addAttribute("calcDate",scService.calcDate());
    return "sample/sc";
  }

  @PostMapping(path = "", params = "calc")
  @OnRejectError(path = "sample/sc",handlingValidationError =  false)
  public String confirm(
    @Validated ScForm form,
    BindingResult bindingResult,
        Model model) {
      if (bindingResult.hasErrors()) {
            model.addAttribute("calcDate",scService.calcDate());
            return "sample/sc";
        }
    model.addAttribute(
    "scResult",scService.calcSc(form.getGenngou(), form.getYear(), form.getSeason()));
    model.addAttribute("calcDate",scService.calcDate());

    return "sample/sc";

  }
}

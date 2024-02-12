package com.example.web.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sample")
public class SampleController {

  @GetMapping
  public String index(Model model) {
    return "sample/index";
  }

  @PostMapping
  public String indexBack(Model model) {
    return "sample/index";
  }
}

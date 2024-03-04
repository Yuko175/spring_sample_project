package com.example.web.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sample/test")
public class TestController {

    @GetMapping
  public String index(Model model) {
      return "sample/test";
  }

    @GetMapping("/anc/1")
  public String testAnc1(Model model) {
      return "sample/testAnc1";
  }

    @GetMapping("/anc/2")
  public String testAnc2(Model model) {
      return "sample/testAnc2";
  }



}

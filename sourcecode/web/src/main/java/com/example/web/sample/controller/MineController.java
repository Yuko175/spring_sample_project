package com.example.web.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web.sample.dto.MineDto;
import com.example.web.sample.form.MineForm;
import com.example.web.sample.service.MineService;

@Controller
@RequestMapping("sample/mine")
public class MineController {
    @Autowired
    private MineService mineService;

    @GetMapping
    public String mine(Model model) {
        String mineFound1_1 = "　";
        String mineFound1_2 = "　";
        String mineFound1_3 = "　";
        String mineFound2_1 = "　";
        String mineFound2_2 = "　";
        String mineFound2_3 = "　";
        String mineFound3_1 = "　";
        String mineFound3_2 = "　";
        String mineFound3_3 = "　";
        model.addAttribute("mineResult", new MineDto(mineFound1_1,mineFound1_2,mineFound1_3,mineFound2_1,mineFound2_2,mineFound2_3,mineFound3_1,mineFound3_2,mineFound3_3));
        return "sample/mine";
    }

    @PostMapping("")
    public String execute(@RequestParam("position") String value, @ModelAttribute MineForm form,
            BindingResult bindingResult, Model model) {
        form.setPosition(value);
        model.addAttribute("mineResult", mineService.calcMine(form.getPosition()));
        return "sample/mine";
    }

}



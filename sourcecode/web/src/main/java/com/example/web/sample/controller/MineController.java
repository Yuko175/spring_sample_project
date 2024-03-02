package com.example.web.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.web.sample.dto.MineDto;
import com.example.web.sample.form.MineForm;
import com.example.web.sample.service.MineService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("sample/mine")
public class MineController {

    private static final String IS_PUSHED_ARRAY = "isPushedArray";

    @Autowired
    private MineService mineService;

    //TODO:セッションを用いて動作するように変更しました。
    //TODO:これで結局HTMLから受け取る値は位置情報だけになったのでFormとDtoは分けられると思います。
    @GetMapping
    public String mine(HttpSession session, Model model) {
        MineForm mineForm = new MineForm();
        BeanUtils.copyProperties(new MineDto(), mineForm);
        model.addAttribute("mineForm", mineForm);
        session.setAttribute(IS_PUSHED_ARRAY, mineForm.getIsPushedArray());
        return "sample/mine";
    }

    @PostMapping("")
    public String execute(HttpSession session, MineForm form,
            BindingResult bindingResult, Model model) {
        System.out.println(form.getPosition());

        boolean[][] isPushedArray = (boolean[][]) session.getAttribute(IS_PUSHED_ARRAY);

        MineDto mineDto = mineService.calcMine(form.getPosition(), isPushedArray);

        session.setAttribute(IS_PUSHED_ARRAY, mineDto.getIsPushedArray());

        model.addAttribute("mineForm", mineDto);
        return "sample/mine";
    }




}



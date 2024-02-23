package com.example.web.sample.controller;

import com.example.web.common.errorhandling.OnRejectError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("sample/mine")
public class MineController {

    @GetMapping
    public String mine(Model model) {
        // マス目の情報をもつ配列を生成する。
        return "sample/mine";
    }

    @PostMapping("")
    public String execute(@RequestParam("position") String value) {
        System.out.println(value);
        // valueに応じて、処理を変更させる（元々生成してある配列の情報と照らし合わせる）
        // 地雷だったら地雷情報を返す。地雷でなかったら数字を返す。
        return "sample/mine";
    }

}

package com.example.web.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.web.sample.dto.MineDto;
import com.example.web.sample.service.MineService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Min;

@Controller
@RequestMapping("sample/mine")
public class MineController {


    private static final String CELL_STATUS = "cellStatus";


    @Autowired
    private MineService mineService;

    //TODO:セッションを用いて動作するように変更しました。
    //TODO:これで結局HTMLから受け取る値は位置情報だけになったのでFormとDtoは分けられると思います。
    @GetMapping
    public String mine(HttpSession session, Model model) {
        MineDto mineDto = new MineDto();
        model.addAttribute("mineDto", mineDto);
        //セッション？へ保存
        session.setAttribute(CELL_STATUS, mineDto.getCellStatus());
        return "sample/mine";
    }

    @GetMapping(path = "{position}")
    public String calcMine(HttpSession session, @PathVariable String position,
            BindingResult bindingResult, Model model) {

        //cellStatus取ってきてる
        String[][] cellStatus = (String[][]) session.getAttribute(CELL_STATUS);
        //pushedFieldの生成/更新処理
        MineDto mineDto = mineService.calcMine(position, cellStatus);
        //セッション？へ保存
        session.setAttribute(CELL_STATUS, mineDto.getCellStatus());

        model.addAttribute("mineDto", mineDto);
        return "sample/mine";
    }


    @PostMapping
    public String putFlag(HttpSession session, @RequestParam(name = "value") String position, @RequestParam(name = "name") String param,Model model) {
        System.out.println(position);
        System.out.println(param);
        //htmlからはPositionを持ってくる(画面更新)
        //cellStatus取ってきてる
        String[][] cellStatus = (String[][]) session.getAttribute(CELL_STATUS);
        //pushedFieldの生成/更新処理
        MineDto mineDto = new MineDto();
        if (param.equals("calcMine")) {
            mineDto = mineService.calcMine(position, cellStatus);
        }
        else if (param.equals("putFlag")) {
            mineDto = mineService.putFlag(position, cellStatus);
        }
        else if (param.equals("putNormal")) {
            mineDto = mineService.putNormal(position, cellStatus);
        }
        //セッション？へ保存
        session.setAttribute(CELL_STATUS, mineDto.getCellStatus());

        model.addAttribute("mineDto", mineDto);
        return "sample/mine";

    }



    // @PostMapping(path = "", params = "putNormal")
    // public String putNormal(HttpSession session, MineForm form,
    //         BindingResult bindingResult, Model model) {
    //     //htmlからはPositionを持ってくる(画面更新)
    //     System.out.println(form.getPosition());
    //     //cellStatus取ってきてる
    //     String[][] cellStatus = (String[][]) session.getAttribute(CELL_STATUS);
    //     //pushedFieldの生成/更新処理
    //     MineDto mineDto = mineService.putNormal(form.getPosition(), cellStatus);
    //     //セッション？へ保存
    //     session.setAttribute(CELL_STATUS, mineDto.getCellStatus());

    //     model.addAttribute("mineForm", mineDto);
    //     return "sample/mine";
    // }





}



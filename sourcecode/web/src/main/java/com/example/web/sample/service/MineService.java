package com.example.web.sample.service;

import com.example.web.sample.dto.MineDto;
import com.example.web.sample.dto.ShisokuDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class MineService {
    public MineDto calcMine(String value) {
    String[] mineField = { "1_1", "1_3" };
        boolean isFound = false;
        for (String mine : mineField) {
            if (mine != null && mine.equals(value)) {
                isFound = true;
                break;
            }
        }
        String mineFound1_1 = "　";
        String mineFound1_2 = "　";
        String mineFound1_3 = "　";
        String mineFound2_1 = "　";
        String mineFound2_2 = "　";
        String mineFound2_3 = "　";
        String mineFound3_1 = "　";
        String mineFound3_2 = "　";
        String mineFound3_3 = "　";
        System.out.println(value);
        if (value.equals("1_1")) {
            mineFound1_1 = "１";
            if (isFound) {
            mineFound1_1 = "×";
            }
        }
        if (value.equals("1_2")) {
            mineFound1_2 = "２";
            if (isFound) {
            mineFound1_2 = "×";
            }
        }
        if (value.equals("1_3")) {
            mineFound1_3 = "３";
            if (isFound) {
            mineFound1_3 = "×";
            }
        }
        if (value.equals("2_1")) {
            mineFound2_1 = "４";
            if (isFound) {
            mineFound2_1 = "×";
            }
        }
        if (value.equals("2_2")) {
            mineFound2_2 = "５";
            if (isFound) {
            mineFound2_2 = "×";
            }
        }
        if (value.equals("2_3")) {
            mineFound2_3 = "６";
            if (isFound) {
            mineFound2_3 = "×";
            }
        }
        if (value.equals("3_1")) {
            mineFound3_1 = "７";
            if (isFound) {
            mineFound3_1 = "×";
            }
        }
        if (value.equals("3_2")) {
            mineFound3_2 = "８";
            if (isFound) {
            mineFound3_2 = "×";
            }
        }
        if (value.equals("3_3")) {
            mineFound3_3 = "９";
            if (isFound) {
            mineFound3_3 = "×";
            }
        }

    return new MineDto(mineFound1_1,mineFound1_2,mineFound1_3,mineFound2_1,mineFound2_2,mineFound2_3,mineFound3_1,mineFound3_2,mineFound3_3);
    }
}

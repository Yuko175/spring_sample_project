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
        String mineFound = isFound ? "⚪︎" : "×";
    return new MineDto(mineFound);
    }
}

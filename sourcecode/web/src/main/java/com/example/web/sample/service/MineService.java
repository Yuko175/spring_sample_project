package com.example.web.sample.service;

import com.example.web.sample.dto.MineDto;
import com.example.web.sample.dto.ShisokuDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class MineService {

    final boolean[][] IS_MINE_ARRAY = {
        { true, false, true },
        { false, false, false },
        { false, false, false }
    };

    public MineDto calcMine(String value ,boolean[][] isPushedArray) {
        String[][] mineArray = {
            { "⚪︎", "⚪︎", "⚪︎" },
            { "⚪︎", "⚪︎", "⚪︎" },
            { "⚪︎", "⚪︎", "⚪︎" }
        };

        int row = Integer.parseInt(value.split("_")[0]);
        int column = Integer.parseInt(value.split("_")[1]);

        for (int row_i = 0; row_i < IS_MINE_ARRAY.length; row_i++) {
            for (int column_i = 0; column_i < IS_MINE_ARRAY[row_i].length; column_i++) {
                if (IS_MINE_ARRAY[row_i][column_i] && row == row_i && column == column_i) {
                    isPushedArray[row_i][column_i] = true;
                    break;
                }
            }
        }

        for (int row_i = 0; row_i < isPushedArray.length; row_i++) {
            for (int column_i = 0; column_i < isPushedArray[row_i].length; column_i++) {
                if (isPushedArray[row_i][column_i]) {
                    mineArray[row_i][column_i] = "×";
                }
            }
        }

    return new MineDto(value, mineArray, isPushedArray);
    }
}

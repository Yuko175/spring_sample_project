package com.example.web.sample.service;

import com.example.web.sample.dto.MineDto;
import com.example.web.sample.dto.ShisokuDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class MineService {

    //地雷の位置
    final boolean[][] IS_MINE_ARRAY = {
        { true, false, true },
        { false, false, false },
        { false, false, false }
    };

    //地雷の裏表示
    public MineDto calcMine(String value, String[][] cellStatus) {
        String[][] pushedField = {
                { "１", "２", "３" },
                { "４", "５", "６" },
                { "７", "８", "９" }
        };

        //地雷の裏表示の変更
        //地雷の書いてあるtrue&falseの値分だけ、回す
        for (int row_i = 0; row_i < IS_MINE_ARRAY.length; row_i++) {
            for (int column_i = 0; column_i < IS_MINE_ARRAY[row_i].length; column_i++) {
                //地雷がある時pushedFieldを"×"にする
                if (IS_MINE_ARRAY[row_i][column_i]) {
                    pushedField[row_i][column_i] = "×";
                }
            }
        }

        //マップ
        int row = Integer.parseInt(value.split("_")[0]);
        int column = Integer.parseInt(value.split("_")[1]);
        cellStatus[row][column] = "pushed";

        return new MineDto(value, pushedField, cellStatus);
    }

    public MineDto putFlag(String value, String[][] cellStatus) {

        String[][] pushedField = {
                { "１", "２", "３" },
                { "４", "５", "６" },
                { "７", "８", "９" }
        };

        //地雷の裏表示の変更
        //地雷の書いてあるtrue&falseの値分だけ、回す
        for (int row_i = 0; row_i < IS_MINE_ARRAY.length; row_i++) {
            for (int column_i = 0; column_i < IS_MINE_ARRAY[row_i].length; column_i++) {
                //地雷がある時pushedFieldを"×"にする
                if (IS_MINE_ARRAY[row_i][column_i]) {
                    pushedField[row_i][column_i] = "×";
                }
            }
        }
        //フラグを立てる
        int row = Integer.parseInt(value.split("_")[0]);
        int column = Integer.parseInt(value.split("_")[1]);
        cellStatus[row][column] = "flag";

        return new MineDto(value, pushedField, cellStatus);

    }
public MineDto putNormal(String value, String[][] cellStatus) {

        String[][] pushedField = {
                { "１", "２", "３" },
                { "４", "５", "６" },
                { "７", "８", "９" }
        };

        //地雷の裏表示の変更
        //地雷の書いてあるtrue&falseの値分だけ、回す
        for (int row_i = 0; row_i < IS_MINE_ARRAY.length; row_i++) {
            for (int column_i = 0; column_i < IS_MINE_ARRAY[row_i].length; column_i++) {
                //地雷がある時pushedFieldを"×"にする
                if (IS_MINE_ARRAY[row_i][column_i]) {
                    pushedField[row_i][column_i] = "×";
                }
            }
        }
        //フラグを立てる
        int row = Integer.parseInt(value.split("_")[0]);
        int column = Integer.parseInt(value.split("_")[1]);
        cellStatus[row][column] = "normal";

        return new MineDto(value, pushedField, cellStatus);

}
}

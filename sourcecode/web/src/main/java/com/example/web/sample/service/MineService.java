package com.example.web.sample.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class MineService {
    // 初期値normalの設定
    public String[][] setNormal(String[][] cellStatus) {
        for (int i = 0; i < cellStatus.length; i++) {
            for (int j = 0; j < cellStatus[i].length; j++) {
                cellStatus[i][j] = "normal";
            }
        }
        return cellStatus;
    }

    //地雷の裏表示(地雷"×"、その他"⚪︎")
    public String[][] makePushedField(String[][] pushedField) {
        Random random = new Random();
        for (int row_i = 0; row_i < pushedField.length; row_i++) {
            for (int column_i = 0; column_i < pushedField[row_i].length; column_i++) {
                //30%で"×"
                if (random.nextInt(100) < 30) {
                    pushedField[row_i][column_i] = "×";
                }
                else {
                    pushedField[row_i][column_i] = "⚪︎";
                }
            }
        }
        return pushedField;
    }

    //押したか判定
    public String[][] openCell(String value, String[][] cellStatus) {
        return updateCellStatus(value, cellStatus, "pushed");
    }

    //旗を立てる
    public String[][] putFlag(String value, String[][] cellStatus) {
        return updateCellStatus(value, cellStatus, "flag");
    }

    //旗を取る
    public String[][] removeFlag(String value, String[][] cellStatus) {
        return updateCellStatus(value, cellStatus, "normal");
    }

    //上記3つのstatusの更新(計算の中身)
    private String[][] updateCellStatus(String value, String[][] cellStatus, String statusName) {
        int row = Integer.parseInt(value.split("_")[0]);
        int column = Integer.parseInt(value.split("_")[1]);
        cellStatus[row][column] = statusName;

        return cellStatus;
    }
}

package com.example.web.sample.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class MineService {
    // 初期値normalの設定
    public String[][] setNormal(String[][] cellStatus) {
        for (int row_i = 0; row_i < cellStatus.length; row_i++) {
            for (int column_i = 0; column_i < cellStatus[row_i].length; column_i++) {
                cellStatus[row_i][column_i] = "normal";
                if (row_i == 0 || row_i == cellStatus.length - 1 || column_i == 0
                            || column_i == cellStatus.length - 1) {
                        cellStatus[row_i][column_i] = "pushed";
                    }
            }
        }
        return cellStatus;
    }

    //地雷の裏表示
    public String[][] makePushedField(String[][] pushedField) {
        return makeMineField(pushedField);
    }

    //地雷の裏表示(地雷"×"、その他"⚪︎")
    private String[][] makeMineField(String[][] pushedField) {
        boolean isSetMineCompleted = false;
        int mineCount = 0;
        Random random = new Random();
        //TODO:・すでに "×"がある場合
        //TODO:・row_i == 0 || row_i == pushedField.length - 1 || column_i == 0   || column_i == pushedField.length - 1の場合
        //TODO:・positionの周り8マスである場合
        //TODO:　　　　　↓
        //TODO:ランダムでpushedField[i][j] = "×"にする。
        while (!isSetMineCompleted) {
            // pushedField、mineCountをリセット
            pushedField = new String[pushedField.length][pushedField.length];
            mineCount = 0;
            for (int row_i = 0; row_i < pushedField.length; row_i++) {
                for (int column_i = 0; column_i < pushedField[row_i].length; column_i++) {
                    //40%で"×"
                    if (random.nextInt(100) < 35) {
                        pushedField[row_i][column_i] = "×";
                    } else {
                        pushedField[row_i][column_i] = "　";
                    }
                    if (row_i == 0 || row_i == pushedField.length - 1 || column_i == 0
                            || column_i == pushedField.length - 1) {
                        pushedField[row_i][column_i] = "　";
                    }
                    if (pushedField[row_i][column_i].equals("×")) {
                        mineCount++;
                    }
                }
            }
            //25~30%の確率で"×"
            int fieldSize = (int) Math.pow(pushedField.length - 2, 2);
            int maxMineLimit = (int) Math.round(fieldSize * 0.30);
            int minMineLimit = (int) Math.round(fieldSize * 0.20);
            if (minMineLimit <= mineCount && mineCount <= maxMineLimit) {
                isSetMineCompleted = true;
                System.out.println(mineCount);
            }
        }

        //数字の計算
        int[][] numberField = new int[pushedField.length][pushedField.length];
        numberField = makeNumberField(pushedField, numberField);

        //pushedFieldに数字を入れる(0または地雷(-1))以外)
        for (int row_i = 0; row_i < numberField.length; row_i++) {
            for (int column_i = 0; column_i < numberField[row_i].length; column_i++) {
                if (numberField[row_i][column_i] > 0) {
                    pushedField[row_i][column_i] = Integer.toString(numberField[row_i][column_i]);
                }
            }
        }
        return pushedField;

    }

    //地雷の裏表示(数字)
    private int[][] makeNumberField(String[][] pushedField,int[][] numberField ) {
        for (int row_i = 0; row_i < pushedField.length; row_i++) {
            for (int column_i = 0; column_i < pushedField[row_i].length; column_i++) {
                if (pushedField[row_i][column_i].equals("×")) {
                    numberField[row_i][column_i + 1]++;
                    numberField[row_i][column_i - 1]++;
                    numberField[row_i - 1][column_i]++;
                    numberField[row_i + 1][column_i]++;
                    numberField[row_i + 1][column_i + 1]++;
                    numberField[row_i + 1][column_i - 1]++;
                    numberField[row_i - 1][column_i + 1]++;
                    numberField[row_i - 1][column_i - 1]++;
                }
            }
        }
        //地雷の場所は-1にする
        for (int row_i = 0; row_i < pushedField.length; row_i++) {
            for (int column_i = 0; column_i < pushedField[row_i].length; column_i++) {
                if (pushedField[row_i][column_i].equals("×")) {
                    numberField[row_i][column_i] = -1;
                }
            }
        }
        return numberField;
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

    //終了判定：gameStatus = "gameOver";
    //newPushedFieldとnewCellStatusの要素の要素を比べ、pushed="×"だと終了
    public String checkGameStatus(String[][] pushedField, String[][] cellStatus, String gameStatus) {
        //失敗判定
        int safeFieldCount = 0;
        int pushCount = 0;
        for (int row_i = 0; row_i < pushedField.length; row_i++) {
            for (int column_i = 0; column_i < pushedField[row_i].length; column_i++) {
                //"⚪︎"の時
                if (!pushedField[row_i][column_i].equals("×")) {
                    safeFieldCount++;
                    //押した
                    if (cellStatus[row_i][column_i].equals("pushed")) {
                        pushCount++;
                    }
                //"×"の時、押した
                } else if (cellStatus[row_i][column_i].equals("pushed")) {
                    gameStatus = "gameOver";
                }
            }
        }

        //クリア判定
        //"gameOver"になっていないことを確認後、"gameClear"にする
        if (gameStatus != "gameOver" && safeFieldCount == pushCount) {
            gameStatus = "gameClear";
        }

        return gameStatus;
    }
}


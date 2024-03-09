package com.example.web.sample.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.web.sample.controller.MineController;

@Service
public class MineService {
    //! 初期値normalの設定
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

    //!地雷の裏表示
    public String[][] makePushedField(String value, String[][] pushedField) {
        //地雷の裏表示(地雷"×"、その他"⚪︎")
        String[][] newPushedField = makeMineField(value, pushedField);

        //地雷の裏表示(数字の計算)
        int[][] numberField = new int[pushedField.length][pushedField.length];
        numberField = makeNumberField(pushedField, numberField);

        //pushedFieldに数字を入れる(0または地雷(-1))以外)
        for (int row_i = 0; row_i < numberField.length; row_i++) {
            for (int column_i = 0; column_i < numberField[row_i].length; column_i++) {
                if (numberField[row_i][column_i] > 0) {
                    newPushedField[row_i][column_i] = Integer.toString(numberField[row_i][column_i]);
                }
            }
        }

        return newPushedField;
    }

    //*地雷の裏表示(地雷"×"、その他"⚪︎")
    private String[][] makeMineField(String value, String[][] pushedField) {
        Random random = new Random();
        int fieldSize = (int) Math.pow(pushedField.length - 2, 2);
        int totalMineCount = (int) Math.round(fieldSize * MineController.MINE_PROBABILITY);
        int mineCount = 0;
        while (mineCount < totalMineCount) {
            int randomRow = random.nextInt(pushedField.length - 2) + 1;
            int randomCol = random.nextInt(pushedField.length - 2) + 1;
            int row = Integer.parseInt(value.split("_")[0]);
            int column = Integer.parseInt(value.split("_")[1]);
            if (pushedField[randomRow][randomCol] != "×" &&
                    !(row == randomRow && column == randomCol) &&
                    !(row == randomRow && column == randomCol + 1) &&
                    !(row == randomRow && column == randomCol - 1) &&
                    !(row == randomRow + 1 && column == randomCol) &&
                    !(row == randomRow - 1 && column == randomCol) &&
                    !(row == randomRow + 1 && column == randomCol + 1) &&
                    !(row == randomRow - 1 && column == randomCol - 1) &&
                    !(row == randomRow + 1 && column == randomCol - 1) &&
                    !(row == randomRow - 1 && column == randomCol + 1)) {
                pushedField[randomRow][randomCol] = "×";
                mineCount++;
            }
        }
        //"×"以外と外側1周空白
        for (int row_i = 0; row_i < pushedField.length; row_i++) {
            for (int column_i = 0; column_i < pushedField[row_i].length; column_i++) {
                if (pushedField[row_i][column_i] != "×") {
                    pushedField[row_i][column_i] = "　";
                }
                if (row_i == 0 || row_i == pushedField.length - 1 || column_i == 0
                        || column_i == pushedField.length - 1) {
                    pushedField[row_i][column_i] = "　";
                }
            }
        }
        return pushedField;

    }

    //*地雷の裏表示(数字の計算)
    private int[][] makeNumberField(String[][] pushedField,int[][] numberField ) {
        for (int row_i = 0; row_i < pushedField.length; row_i++) {
            for (int column_i = 0; column_i < pushedField[row_i].length; column_i++) {
                //8方向に数字を足す
                int[][] moveCell = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
                for (int[] move : moveCell) {
                    int newRow = row_i + move[0];
                    int newCol = column_i + move[1];
                    if(pushedField[row_i][column_i].equals("×")){
                        numberField[newRow][newCol]++;
                    }
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

    //!押したか判定
    public String[][] openCell(String value, String[][] cellStatus) {
        return updateCellStatus(value, cellStatus, "pushed");
    }

    //!旗を立てる
    public String[][] putFlag(String value, String[][] cellStatus) {
        return updateCellStatus(value, cellStatus, "flag");
    }

    //!旗を取る
    public String[][] removeFlag(String value, String[][] cellStatus) {
        return updateCellStatus(value, cellStatus, "normal");
    }

    //*上記3つのstatusの更新(計算の中身)
    private String[][] updateCellStatus(String value, String[][] cellStatus, String statusName) {
        int row = Integer.parseInt(value.split("_")[0]);
        int column = Integer.parseInt(value.split("_")[1]);
        cellStatus[row][column] = statusName;

        return cellStatus;
    }

    //!空白マスの周りを開ける
    public String[][] pushedBlankOpenAround(String value, String[][] newPushedField, String[][] newCellStatus) {
        int row = Integer.parseInt(value.split("_")[0]);
        int column = Integer.parseInt(value.split("_")[1]);
        if (row > 0 &&
                row < newPushedField.length - 1 &&
                column > 0 &&
                column < newPushedField.length - 1 &&
                newPushedField[row][column].equals("　") &&
                newCellStatus[row][column].equals("pushed")) {
            //8方向の確認
            int[][] moveCell = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
            for (int[] move : moveCell) {
                int newRow = row + move[0];
                int newColumn = column + move[1];
                if (!newCellStatus[newRow][newColumn].equals("pushed")) {
                    newCellStatus[newRow][newColumn] = "pushed";
                    newCellStatus = pushedBlankOpenAround(newRow + "_" + newColumn, newPushedField, newCellStatus);
                }
            }
        }
        return newCellStatus;
    }


    public int calcRemainingFlags(String[][] cellStatus, int allFlags) {
        int flagCount = 0;
        for (int row_i = 0; row_i < cellStatus.length; row_i++) {
            for (int column_i = 0; column_i < cellStatus[row_i].length; column_i++) {
                if(cellStatus[row_i][column_i].equals("flag")){
                    flagCount++;
                }

            }
        }
        int remainingFlags = allFlags - flagCount;

        return remainingFlags;
    }


    //!終了判定
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
                } else if (!gameStatus.equals("gameClear") && cellStatus[row_i][column_i].equals("pushed")) {
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


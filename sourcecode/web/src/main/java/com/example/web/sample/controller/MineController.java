package com.example.web.sample.controller;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.web.sample.dto.MineDto;
import com.example.web.sample.service.MineService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("sample/mine")
public class MineController {

    //マス目の大きさ(px)
    public static final int CELL_SIZE = 30;
    public static final int FONT_SIZE = 16;

    //地雷の出現確率(端数四捨五入)
    public static final double MINE_PROBABILITY = 0.2;//20%

    //難易度別のマスの一辺
    public static final int LEVEL_BASIC = 7;
    public static final int LEVEL_STANDARD = 10;
    public static final int LEVEL_ADVANCE = 13;

    //変数名を間違えないように定義(セッションに登録する名前)
    private static final String FIELD_SIZE = "fieldSize";
    private static final String CELL_STATUS = "cellStatus";
    private static final String PUSHED_FIELD = "pushedField";
    private static final String GAME_STATUS = "gameStatus";
    private static final String IS_FIRST_CLICK_COMPLETED = "isFirstClickCompleted";
    private static final String IS_GAME_FINISHED = "isGameFinished";
    private static final String All_FLAGS = "allFlags";


    @Autowired
    private MineService mineService;

    @GetMapping
    public String mine(HttpSession session, Model model) {
        //初期値をsessionに詰める
        session.setAttribute("cellSize", CELL_SIZE);
        session.setAttribute("fontSize", FONT_SIZE);
        session.setAttribute("levelBasic", LEVEL_BASIC);
        session.setAttribute("levelStandard", LEVEL_STANDARD);
        session.setAttribute("levelAdvance", LEVEL_ADVANCE);
        session.setAttribute(IS_FIRST_CLICK_COMPLETED, false);
        session.setAttribute(IS_GAME_FINISHED, false);

        //Dtoに必要な値を代入し、sessionに詰める
        MineDto newMineDto = new MineDto();
        //FIELD_SIZE
        int fieldSize = LEVEL_STANDARD;
        session.setAttribute(FIELD_SIZE, fieldSize);
        //PUSHED_FIELD
        String[][] pushedField = new String[fieldSize][fieldSize];
        newMineDto.setPushedField(pushedField);
        session.setAttribute(PUSHED_FIELD, pushedField);
        //CELL_STATUS
        String[][] cellStatus = new String[fieldSize][fieldSize];
        String[][] newCellStatus = mineService.setNormal(cellStatus);
        newMineDto.setCellStatus(newCellStatus);
        session.setAttribute(CELL_STATUS, newMineDto.getCellStatus());
        //GAME_STATUS
        session.setAttribute(GAME_STATUS, newMineDto.getGameStatus());
        //REMAINING_FLAGS
        int allFlags = (int) Math.round((int) Math.pow(fieldSize - 2, 2) * MineController.MINE_PROBABILITY);
        newMineDto.setRemainingFlags(allFlags);
        session.setAttribute(All_FLAGS, newMineDto.getRemainingFlags());


        //Dtoに詰める
        model.addAttribute("mineDto", newMineDto);

        return "sample/mine";
    }

    @PostMapping
    public String pushedCell(HttpSession session, @RequestParam(name = "value") String position,
            @RequestParam(name = "name") String param, Model model) {

        //!難易度を変える(マスの大きさを変える)
        if (param.equals("changeLevel")) {
            changeLevel(session, position, model);
        } else {
            //!ゲームをする
            updateGame(session, position, param, model);
        }

        return "sample/mine";
    }

    //*難易度を変える(マスの大きさを変える)
    private void changeLevel(HttpSession session, String position, Model model) {
        //初期値をsessionに詰める
        session.setAttribute("cellSize", CELL_SIZE);
        session.setAttribute("fontSize", FONT_SIZE);
        session.setAttribute(IS_FIRST_CLICK_COMPLETED, false);
        session.setAttribute(IS_GAME_FINISHED, false);

        //fieldSizeの決定
        int fieldSize = Integer.parseInt(position);
        session.setAttribute(FIELD_SIZE, fieldSize);

        //Dtoに必要な値を代入し、sessionに詰める
        MineDto newMineDto = new MineDto();
        //PUSHED_FIELD
        String[][] pushedField = new String[fieldSize][fieldSize];
        newMineDto.setPushedField(pushedField);
        session.setAttribute(PUSHED_FIELD, pushedField);
        //CELL_STATUS
        String[][] cellStatus = mineService.setNormal(new String[fieldSize][fieldSize]);
        newMineDto.setCellStatus(cellStatus);
        session.setAttribute(CELL_STATUS, newMineDto.getCellStatus());
        //GAME_STATUS
        session.setAttribute(GAME_STATUS, newMineDto.getGameStatus());
        //REMAINING_FLAGS
        int allFlags = (int) Math.round((int) Math.pow(fieldSize - 2, 2) * MineController.MINE_PROBABILITY);
        newMineDto.setRemainingFlags(allFlags);
        session.setAttribute(All_FLAGS, newMineDto.getRemainingFlags());

        //Dtoを返す
        model.addAttribute("mineDto", newMineDto);
    }


    //*ゲームをする
    private void updateGame(HttpSession session, String position, String param, Model model) {
        //fieldSizeを取得
        int fieldSize = (int) session.getAttribute(FIELD_SIZE);

        //更新値をDtoに詰めるため、インスタンスを新規作成
        MineDto forUpdateMineDto = new MineDto();

        //1回目であれば、地雷をセット(裏画面作成)
        boolean isFirstClickCompleted = (boolean) session.getAttribute(IS_FIRST_CLICK_COMPLETED);
        String[][] oldPushedField = (String[][]) session.getAttribute(PUSHED_FIELD);
        String[][] newPushedField = new String[fieldSize][fieldSize];
        if (!isFirstClickCompleted) {
            //TODO:putflagを行うたびにmineService.makePushedFieldが行われる
            //TODO:マス目が大きくなった時に処理が重くなる可能性あり
            newPushedField = mineService.makePushedField(position, oldPushedField);
            if (param.equals("openCell")) {
                session.setAttribute(PUSHED_FIELD, newPushedField);
                session.setAttribute(IS_FIRST_CLICK_COMPLETED, true);
            }
        } else {
            //変更がなければ変更せずにそのままsetする
            newPushedField = oldPushedField;
        }
        forUpdateMineDto.setPushedField(newPushedField);

        //cellStatusの生成/更新処理
        String[][] oldCellStatus = (String[][]) session.getAttribute(CELL_STATUS);
        String[][] newCellStatus = new String[fieldSize][fieldSize];
        switch (param) {
            case "openCell":
                newCellStatus = mineService.openCell(position, oldCellStatus);
                break;
            case "putFlag":
                newCellStatus = mineService.putFlag(position, oldCellStatus);
                break;
            case "removeFlag":
                newCellStatus = mineService.removeFlag(position, oldCellStatus);
                break;
            default:
                newCellStatus = oldCellStatus;
        }
        //空白マスが空いた時のcellStatusの更新
        newCellStatus = mineService.pushedBlankOpenAround(position, newPushedField, newCellStatus);
        forUpdateMineDto.setCellStatus(newCellStatus);
        session.setAttribute(CELL_STATUS, forUpdateMineDto.getCellStatus());

        //残りの旗数を計算
        int allFlags =(int)session.getAttribute(All_FLAGS);
        int newRemainingFlags = mineService.calcRemainingFlags(newCellStatus, allFlags);
        forUpdateMineDto.setRemainingFlags(newRemainingFlags);


        //終了判定
        String oldGameStatus = (String) session.getAttribute(GAME_STATUS);
        String newGameStatus = mineService.checkGameStatus(newPushedField, newCellStatus, oldGameStatus);
        forUpdateMineDto.setGameStatus(newGameStatus);
        session.setAttribute(GAME_STATUS, forUpdateMineDto.getGameStatus());

        //クリア後、全てのマスを押された判定とし、解答を出す
        if (newGameStatus.equals("gameClear")) {
            for (int row_i = 0; row_i < newCellStatus.length; row_i++) {
                Arrays.fill(newCellStatus[row_i], "pushed");
            }
        }

        model.addAttribute("mineDto", forUpdateMineDto);
    }
}



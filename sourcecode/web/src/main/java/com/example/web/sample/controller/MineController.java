package com.example.web.sample.controller;


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

    public static final int FIELD_SIZE = 8;//2以下はNG
    public static final int CELL_SIZE = 35;

    private static final String CELL_STATUS = "cellStatus";
    private static final String PUSHED_FIELD = "pushedField";
    private static final String IS_FIRST_CLICK_COMPLETED = "isFirstClickCompleted";
    private static final String IS_GAME_FINISHED = "isGameFinished";


    @Autowired
    private MineService mineService;

    @GetMapping
    public String mine(HttpSession session, Model model) {
        //初期値をsessionに詰める
        session.setAttribute("fieldSize", FIELD_SIZE);
        session.setAttribute("cellSize", CELL_SIZE);
        session.setAttribute(IS_FIRST_CLICK_COMPLETED, false);
        session.setAttribute(IS_GAME_FINISHED, false);

        //Dtoに必要な値を代入し、sessionに詰める
        MineDto newMineDto = new MineDto();
        //PUSHED_FIELD
        session.setAttribute(PUSHED_FIELD, newMineDto.getPushedField());
        //CELL_STATUS
        String[][] newCellStatus = mineService.setNormal(newMineDto.getCellStatus());
        newMineDto.setCellStatus(newCellStatus);
        session.setAttribute(CELL_STATUS, newMineDto.getCellStatus());

        //Dtoに詰める
        model.addAttribute("mineDto", newMineDto);

        return "sample/mine";
    }

    @PostMapping
    public String putFlag(HttpSession session, @RequestParam(name = "value") String position,
            @RequestParam(name = "name") String param, Model model) {

        //更新値をDtoに詰めるため、インスタンスを新規作成
        MineDto forUpdateMineDto = new MineDto();

        //1回目であれば、地雷をセット(裏画面作成)
        boolean isFirstClickCompleted = (boolean) session.getAttribute(IS_FIRST_CLICK_COMPLETED);
        String[][] oldPushedField = (String[][]) session.getAttribute(PUSHED_FIELD);
        String[][] newPushedField = new String[FIELD_SIZE][FIELD_SIZE];
        if (!isFirstClickCompleted) {
            newPushedField = mineService.makePushedField(oldPushedField);
            session.setAttribute(PUSHED_FIELD, newPushedField);
            session.setAttribute(IS_FIRST_CLICK_COMPLETED, true);
        } else {
            //変更がなければ変更せずにそのままsetする
            newPushedField = oldPushedField;
        }
        forUpdateMineDto.setPushedField(newPushedField);

        //cellStatusの生成/更新処理
        String[][] oldCellStatus = (String[][]) session.getAttribute(CELL_STATUS);
        String[][] newCellStatus = new String[FIELD_SIZE][FIELD_SIZE];
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
        }
        forUpdateMineDto.setCellStatus(newCellStatus);
        session.setAttribute(CELL_STATUS, forUpdateMineDto.getCellStatus());

        //positionは初期値(null)のままで返す
        model.addAttribute("mineDto", forUpdateMineDto);

        //終了判定
        String newGameStatus = forUpdateMineDto.getGameStatus();
        forUpdateMineDto.setGameStatus(mineService.checkGameStatus(newPushedField,newCellStatus,newGameStatus));

        return "sample/mine";
    }
}

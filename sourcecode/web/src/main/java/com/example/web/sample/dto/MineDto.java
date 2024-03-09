package com.example.web.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.web.sample.controller.MineController;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MineDto {
    private String position = "";
    private String[][] pushedField;
    private String[][] cellStatus;
    private String gameStatus = "play";
}

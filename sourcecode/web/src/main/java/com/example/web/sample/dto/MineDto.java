package com.example.web.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MineDto {
    private String position = "";
    private String[][] pushedField;
    private String[][] cellStatus;
    private String gameStatus = "play";
    private int remainingFlags;
}

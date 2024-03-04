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
    private static final int FIELD_SIZE = MineController.FIELD_SIZE;
    private String position = "";
    private String[][] pushedField = new String[FIELD_SIZE][FIELD_SIZE];
    private String[][] cellStatus = new String[FIELD_SIZE][FIELD_SIZE];
}

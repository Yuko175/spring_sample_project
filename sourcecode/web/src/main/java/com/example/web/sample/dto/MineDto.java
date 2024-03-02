package com.example.web.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MineDto {
    private String position = "　";
    private String[][] mineArray = {
            { "　", "　", "　" },
            { "　", "　", "　" },
            { "　", "　", "　" }
    };
    private boolean[][] isPushedArray = {
            { false, false, false },
            { false, false, false },
            { false, false, false }
    };

    public MineDto(String position, String[][] mineArray, boolean[][] isPushedArray) {
        this.position = position;
        this.mineArray = mineArray;
        this.isPushedArray = isPushedArray;
    }
}

package com.example.web.sample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountDto {

    private int spaceCharCount;
    private int noSpaceCharCount;
    private int lineCount;
    private int paragraphCount;
    private int diffCount;
    private boolean isMinus;

    public CountDto() {
        this.spaceCharCount = 0;
        this.noSpaceCharCount = 0;
        this.lineCount = 0;
        this.paragraphCount = 0;
        this.diffCount = 0;
        this.isMinus = false;
    }

    public CountDto (
        int spaceCharCount,
        int noSpaceCharCount,
        int lineCount,
        int paragraphCount,
        int diffCount) {
        this.spaceCharCount = spaceCharCount;
        this.noSpaceCharCount = noSpaceCharCount;
        this.lineCount = lineCount;
        this.paragraphCount = paragraphCount;
        this.diffCount = Math.abs(diffCount);
        if (diffCount < 0) {
            this.isMinus = true;
        }
    }

}





package com.example.web.sample.dto;


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
    
    public int getSpaceCharCount() {
        return spaceCharCount;
    }

    public void setSpaceCharCount(int spaceCharCount) {
        this.spaceCharCount = spaceCharCount;
    }
    
    public int getNoSpaceCharCount() {
        return noSpaceCharCount;
    }

    public void setNoSpaceCharCount(int noSpaceCharCount) {
        this.noSpaceCharCount = noSpaceCharCount;
    }
    
    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }
    
    public int getParagraphCount() {
        return paragraphCount;
    }

    public void setParagraphCount(int paragraphCount) {
        this.paragraphCount = paragraphCount;
    }
    
    public int getDiffCount() {
        return diffCount;
    }

    public void setDiffCount(int diffCount) {
        this.diffCount = diffCount;
    }
    
    public boolean getIsMinus() {
        return isMinus;
    }

    public void setIsMinus(boolean isMinus) {
    this.isMinus = isMinus;
    }
}





package com.example.web.sample.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScDateDto {
    private int betweenDays;
    private LocalDate examDate;
    private int examYear;
    private int examMonth;
    private int examDay;

    public ScDateDto (
        int betweenDays,
        LocalDate  examDate,
        int examYear,
        int examMonth,
        int examDay) {
        this.betweenDays = betweenDays;
        this.examDate = examDate;
        this.examYear = examYear;
        this.examMonth = examMonth;
        this.examDay = examDay;
    }


}






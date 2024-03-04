package com.example.web.sample.service;

import com.example.web.sample.dto.ScDateDto;
import com.example.web.sample.dto.ScDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class ScService {
    public ScDto calcSc (
        String genngou,
        Integer year,
        String season) {

        //問題リンク
        String formatYear = String.format("%02d", year);
        String seasonStr = "";
        if (season.equals("春期")) {
            seasonStr = "haru";
        } else if (season.equals("秋期")) {
            seasonStr = "aki";
        }

        String gogoUrl = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr;
        String gogo1_1 = "";
        String gogo1_2 = "";
        String gogo1_3 = "";
        String gogo2_1 = "";
        String gogo2_2 = "";
        if (genngou.equals("令和") && formatYear.equals("05") && season.equals("秋期")) {
            //令和5年度用
            gogo1_1 += gogoUrl + "/pm01.pdf";
            gogo1_2 += gogoUrl + "/pm02.pdf";
            gogo1_3 += gogoUrl + "/pm03.pdf";
            gogo2_1 += gogoUrl + "/pm04.pdf";
        } else {
            //その他
            gogo1_1 = gogoUrl + "/pm1_1.pdf";
            gogo1_2 = gogoUrl + "/pm1_2.pdf";
            gogo1_3 = gogoUrl + "/pm1_3.pdf";
            gogo2_1 = gogoUrl + "/pm2_1.pdf";
            gogo2_2 = gogoUrl + "/pm2_2.pdf";
        }

        //採点リンク
        BigDecimal adYear = BigDecimal.ZERO;
        String genngouStr = "";
        //令和の場合
        if (genngou.equals("令和")) {
            genngouStr = "r";
            BigDecimal reiwaYear = new BigDecimal(year);
            adYear = reiwaYear.add(new BigDecimal(2018));
            //平成の場合
        } else if (genngou.equals("平成")) {
            genngouStr = "h";
            BigDecimal heiseiYear = new BigDecimal(year);
            adYear = heiseiYear.add(new BigDecimal(1988));
        }

        String saitenn = "";
        //令和元年秋の場合
        if (genngou.equals("令和") && formatYear.equals("01") && season.equals("秋期")) {
            saitenn = "https://www.ipa.go.jp/shiken/mondai-kaiotu/" + adYear + "h31.html#haru_sc";
        } else {
            //その他
            saitenn = "https://www.ipa.go.jp/shiken/mondai-kaiotu/" + adYear + genngouStr + formatYear + ".html#"
                    + seasonStr + "_sc";

        }

        return new ScDto(gogo1_1, gogo1_2, gogo1_3, gogo2_1, gogo2_2, saitenn);
    }

    public ScDateDto calcDate() {
        LocalDate nowDate = LocalDate.now();
        LocalDate examDate = LocalDate.of(2024, 4, 21);
        int betweenDays = (int) ChronoUnit.DAYS.between(nowDate, examDate);

        int examYear = examDate.getYear();
        int examMonth = examDate.getMonthValue();
        int examDay = examDate.getDayOfMonth();
        return new ScDateDto(betweenDays,examDate,examYear,examMonth,examDay);
    }

}

package com.example.web.sample.service;

import com.example.web.sample.dto.ScDto;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class ScService {
    public ScDto calcSc (
        String genngou,
        String year,
        String season) {
    
    //問題リンク
    Integer intYear=Integer.parseInt(year);
    String formatYear = String.format("%02d", intYear);

    String gogo1_1 = "";
    String gogo1_2 = "";
    String gogo1_3 = "";
    String gogo2_1 = "";
    String gogo2_2 = "";

    String seasonStr = "";
    if (season.equals("春期")) {
      seasonStr = "haru";

    } else if(season.equals("秋期"))   {
      seasonStr = "aki";
    }

    //TODO:冗長？

    if (genngou.equals("令和") && formatYear.equals("05") && season.equals("秋期")) {
      //令和5年度用
      gogo1_1 = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr + "/pm01.pdf";
      gogo1_2 = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr + "/pm02.pdf";
      gogo1_3 = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr + "/pm03.pdf";
      gogo2_1 = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr + "/pm04.pdf";
      gogo2_2 = "";

    } else {
      //その他
      gogo1_1 = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr + "/pm1_1.pdf";
      gogo1_2 = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr + "/pm1_2.pdf";
      gogo1_3 = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr + "/pm1_3.pdf";
      gogo2_1 = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr + "/pm2_1.pdf";
      gogo2_2 = "https://www.sc-siken.com/pdf/" + formatYear + "_" + seasonStr + "/pm2_2.pdf";
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
    if (genngou.equals("令和")  && formatYear.equals("01")&& season.equals("秋期")) {
        saitenn="https://www.ipa.go.jp/shiken/mondai-kaiotu/"+adYear+"h31.html#"+seasonStr+"_sc";
      } else {
      //その他
        saitenn="https://www.ipa.go.jp/shiken/mondai-kaiotu/"+adYear+genngouStr+formatYear+".html#"+seasonStr+"_sc";

      }


    return new ScDto (gogo1_1,gogo1_2,gogo1_3,gogo2_1,gogo2_2,saitenn);
    }
}

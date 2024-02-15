package com.example.web.sample.dto;



public class ShisokuDto {

    private String wa;
    private String sa;
    private String seki;
    private String shou;

    public ShisokuDto (
        String wa,
        String sa,
        String seki,
            String shou) {
        this.wa = wa;
        this.sa = sa;
        this.seki = seki;
        this.shou = shou;

    }
    
    public String getWa() {
        return wa;
    }

    public void getWa(String wa) {
        this.wa = wa;
    }
    
    public String getSa() {
        return sa;
    }

    public void getSa(String sa) {
        this.sa = sa;
    }
    
        public String getSeki() {
        return seki;
    }

    public void getSeki(String seki) {
        this.seki = seki;
    }
    
            public String getShou() {
        return shou;
    }

    public void getShou(String shou) {
    this.shou = shou;
    }
}





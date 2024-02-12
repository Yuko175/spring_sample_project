package com.example.web.sample.dto;

public class BmiDto {

  private String adequateWeight;
  private String comparison;
  private String bmi;

  public BmiDto(String adequateWeight, String comparison, String bmi) {
    this.adequateWeight = adequateWeight;
    this.comparison = comparison;
    this.bmi = bmi;
  }

  public String getAdequateWeight() {
    return adequateWeight;
  }

  public void setAdequateWeight(String adequateWeight) {
    this.adequateWeight = adequateWeight;
  }

  public String getComparison() {
    return comparison;
  }

  public void setComparison(String comparison) {
    this.comparison = comparison;
  }

  public String getBmi() {
    return bmi;
  }

  public void setBmi(String bmi) {
    this.bmi = bmi;
  }
}

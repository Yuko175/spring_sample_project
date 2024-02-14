package com.example.web.sample.dto;

import java.math.BigDecimal;

public class BmiDto {

  private BigDecimal adequateWeight;
  private BigDecimal comparison;
  private BigDecimal bmi;

  public BmiDto(
      BigDecimal adequateWeight,
      BigDecimal comparison,
      BigDecimal bmi) {
    this.adequateWeight = adequateWeight;
    this.comparison = comparison;
    this.bmi = bmi;
  }

  public BigDecimal getAdequateWeight() {
    return adequateWeight;
  }

  public void setAdequateWeight(BigDecimal adequateWeight) {
    this.adequateWeight = adequateWeight;
  }

  public BigDecimal getComparison() {
    return comparison;
  }

  public void setComparison(BigDecimal comparison) {
    this.comparison = comparison;
  }

  public BigDecimal getBmi() {
    return bmi;
  }

  public void setBmi(BigDecimal bmi) {
    this.bmi = bmi;
  }
}

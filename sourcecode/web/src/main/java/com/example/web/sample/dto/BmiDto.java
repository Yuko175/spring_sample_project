package com.example.web.sample.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BmiDto {
    private BigDecimal adequateWeight;
    private BigDecimal comparison;
    private BigDecimal bmi;
}

package com.softtech.model.responseDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoryGetDto {
    private Long id;
    private String name;
    private BigDecimal vatRate;
}

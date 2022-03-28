package com.softtech.model.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CategoryDetailDto {
    private String name;
    private BigDecimal vatRate;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Double avgPrice;
    private Long productCount;
}

package com.patika.model.requestDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreateDto {
    private String name;
    private BigDecimal price;
}

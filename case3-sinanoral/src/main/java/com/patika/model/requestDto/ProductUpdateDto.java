package com.patika.model.requestDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateDto {
    private BigDecimal price;
}

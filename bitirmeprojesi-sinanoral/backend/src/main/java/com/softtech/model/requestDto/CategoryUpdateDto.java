package com.softtech.model.requestDto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
public class CategoryUpdateDto {
    @DecimalMin(value = "0.0", message = "Vat rate can not be negative!")
    BigDecimal vatRate;
}

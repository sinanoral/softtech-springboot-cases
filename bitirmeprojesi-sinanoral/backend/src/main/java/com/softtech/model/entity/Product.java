package com.softtech.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "PRODUCT")
public class Product extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY_ID")
    private Category category;

    @DecimalMin(value = "0.0001", message = "Price can not be negative or zero!")
    @Column(name = "PRICE", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "VAT_AMOUNT", nullable = false, precision = 19, scale = 2)
    private BigDecimal vatAmount;

    @Column(name = "VAT_INCLUSIVE_PRICE", nullable = false, precision = 19, scale = 2)
    private BigDecimal vatInclusivePrice;
}

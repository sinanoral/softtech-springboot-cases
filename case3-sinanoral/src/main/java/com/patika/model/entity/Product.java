package com.patika.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "PRODUCT")
public class Product extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "PRICE", precision = 19, scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
}

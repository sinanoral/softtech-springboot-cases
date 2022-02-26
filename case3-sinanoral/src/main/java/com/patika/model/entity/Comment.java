package com.patika.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "COMMENT")
public class Comment extends BaseEntity {

    @Column(name = "CONTEXT", nullable = false, length = 240)
    private String context;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User user;
}

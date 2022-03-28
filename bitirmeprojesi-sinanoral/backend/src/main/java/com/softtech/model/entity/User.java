package com.softtech.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "\"USER\"")
public class User extends BaseEntity {

    @Column(name = "USER_NAME", unique = true, nullable = false, length = 30)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @Column(name = "SURNAME", nullable = false, length = 30)
    private String surname;
}

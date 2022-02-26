package com.patika.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "\"USER\"") //user is reversed keyword in postgreSql
public class User extends BaseEntity {

    @Column(name = "EMAIL", length = 35, unique = true)
    private String email;

    @Column(name = "PHONE_NUMBER", length = 25, unique = true)
    private String phoneNumber;

    @Column(name = "USER_NAME", length = 20, unique = true)
    private String userName;

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;
}

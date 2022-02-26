package com.patika.model.requestDto;

import com.patika.model.entity.UserType;
import lombok.Data;

@Data
public class UserCreateDto {
    private String email;
    private String phoneNumber;
    private String userName;
    private UserType userType;
}

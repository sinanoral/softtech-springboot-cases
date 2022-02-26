package com.patika.model.responseDto;

import com.patika.model.entity.UserType;
import lombok.Data;

@Data
public class UserGetDto {
    private String email;
    private String phoneNumber;
    private String userName;
    private UserType userType;
}

package com.softtech.model.requestDto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDto {
    @NotBlank(message = "User name can not be blank!")
    @NotNull(message = "User name can not be null!")
    @Length(min = 3, max = 20, message = "User name should be 3 to 20 character long!")
    private String userName;

    @NotBlank(message = "Password can not be blank!")
    @NotNull(message = "Password can not be null!")
    @Length(min = 6, max = 20, message = "Password should be 6 to 20 character long!")
    private String password;
}

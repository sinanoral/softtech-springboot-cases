package com.softtech.model.requestDto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class UserCreateDto {

    @NotEmpty(message = "User name can not be empty!")
    @NotNull(message = "User name can not be null!")
    @Length(min = 3, max = 20, message = "User name should be 3 to 20 character long!")
    private String userName;

    @NotEmpty(message = "Password can not be empty!")
    @NotNull(message = "Password can not be null!")
    @Length(min = 6, max = 20, message = "Password should be 6 to 20 character long!")
    private String password;

    @NotEmpty(message = "Name can not be empty!")
    @NotNull(message = "Name can not be null!")
    private String name;

    @NotEmpty(message = "Surname can not be empty!")
    @NotNull(message = "Surname can not be null!")
    private String surname;
}

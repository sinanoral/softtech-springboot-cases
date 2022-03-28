package com.softtech.mapper;

import com.softtech.model.entity.User;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.model.requestDto.UserUpdateDto;
import com.softtech.model.responseDto.UserGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = PasswordEncoderMapper.class)
public interface UserMapper {

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User userCreateDtoToUser(UserCreateDto userCreateDto);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User userUpdateDtoToUser(UserUpdateDto userUpdateDto);

    UserGetDto userToUserGetDto(User user);
}


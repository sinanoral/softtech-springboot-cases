package com.patika.service;

import com.patika.dao.UserDao;
import com.patika.enums.errors.UserErrorMessage;
import com.patika.exception.NotFoundException;
import com.patika.mapper.UserMapper;
import com.patika.model.entity.User;
import com.patika.model.requestDto.UserCreateDto;
import com.patika.model.requestDto.UserUpdateDto;
import com.patika.model.responseDto.UserGetDto;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import com.patika.utilities.results.SuccessDataResult;
import com.patika.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapper mapper;

    public DataResult<List<UserGetDto>> getAll() {
        List<User> users = userDao.findAll();
        if (users.isEmpty())
            throw new NotFoundException(UserErrorMessage.USER_NOT_FOUND);

        List<UserGetDto> userGetDtoList = mapper.UserListToUserDtoList(users);
        return new SuccessDataResult<>(userGetDtoList);
    }

    public Result create(UserCreateDto UserCreateDto) {
        User User = mapper.UserCreateDtoToUser(UserCreateDto);
        userDao.save(User);
        return new SuccessResult("User Created!");
    }

    public DataResult<UserGetDto> getById(Long id) {
        User User = getUserWithControl(userDao.findById(id));

        UserGetDto UserGetDto = mapper.UserToUserDto(User);
        return new SuccessDataResult<>(UserGetDto);
    }

    public DataResult<UserGetDto> getByName(String userName) {
        User User = getUserWithControl(userDao.findByUserName(userName));

        UserGetDto UserGetDto = mapper.UserToUserDto(User);
        return new SuccessDataResult<>(UserGetDto);
    }

    @Transactional
    public Result deleteByUserNameAndPhoneNumber(String userName, String phoneNumber) {
        getUserWithControl(userDao.findByUserName(userName));

        Long count = userDao.deleteUserByUserNameAndPhoneNumber(userName, phoneNumber);
        if (count == 0) {
            throw new NotFoundException(UserErrorMessage.USERNAME_AND_PHONE_NOT_MATCH);
        }
        return new SuccessResult("User deleted!");
    }

    @Transactional
    public Result updateUserById(Long id, UserUpdateDto userUpdateDto) {
        User userToUpdate = getUserWithControl(userDao.findById(id));

        userToUpdate.setUserName(userUpdateDto.getUserName());
        userToUpdate.setEmail(userUpdateDto.getEmail());
        userToUpdate.setPhoneNumber(userUpdateDto.getPhoneNumber());
        userToUpdate.setUserType(userUpdateDto.getUserType());

        userDao.save(userToUpdate);

        return new SuccessResult("User updated");
    }

    private User getUserWithControl(Optional<User> userDao) {
        return userDao.orElseThrow(() ->
                new NotFoundException(UserErrorMessage.USER_NOT_FOUND));
    }

    //For mapper to use
    public User findById(Long id) {
        return userDao.getById(id);
    }
}

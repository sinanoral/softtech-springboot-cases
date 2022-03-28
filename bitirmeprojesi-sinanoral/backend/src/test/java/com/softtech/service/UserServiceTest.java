package com.softtech.service;

import com.softtech.dao.UserDao;
import com.softtech.enums.UserErrorMessage;
import com.softtech.exceptions.DuplicateEntityException;
import com.softtech.exceptions.EntityNotFoundException;
import com.softtech.mapper.UserMapper;
import com.softtech.model.entity.User;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.model.requestDto.UserUpdateDto;
import com.softtech.model.responseDto.UserGetDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper mapper;

    @Test
    void whenCreateUserCalledWithValidDto_itShouldReturnUserDto() {

        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUserName("test");

        User user = mock(User.class);

        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setUserName("test");

        when(userDao.existsAllByUserName(userCreateDto.getUserName())).thenReturn(false);
        when(mapper.userCreateDtoToUser(userCreateDto)).thenReturn(user);
        when(userDao.save(user)).thenReturn(user);
        when(mapper.userToUserGetDto(user)).thenReturn(userGetDto);

        UserGetDto result = userService.createUser(userCreateDto);

        assertEquals("test", result.getUserName());
    }

    @Test
    void whenCreateUserCalledWithDuplicateUserName_itShouldThrowDuplicateException() {
        UserCreateDto userCreateDto = mock(UserCreateDto.class);

        when(userCreateDto.getUserName()).thenReturn("test");
        when(userDao.existsAllByUserName(any())).thenReturn(true);
        assertThrows(DuplicateEntityException.class, () -> userService.createUser(userCreateDto));
    }

    @Test
    void whenGetUserByIdWithControlCalledWithValidId_itShouldReturnUser() {
        User user = mock(User.class);

        when(user.getId()).thenReturn(1L);
        when(userDao.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserByIdWithControl(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void whenGetUserByIdWithControlCalledWithInvalidId_itShouldThrowNotFoundException() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.getUserByIdWithControl(1L));

        assertEquals(UserErrorMessage.USER_NOT_FOUND_ID, exception.getBaseErrorMessage());
    }

    @Test
    void whenGetUserByUserNameWithControlCalledWithValidId_itShouldReturnUser() {
        User user = mock(User.class);

        when(user.getUserName()).thenReturn("test");
        when(userDao.findByUserName("test")).thenReturn(Optional.of(user));

        User result = userService.getUserByUserNameWithControl("test");

        assertEquals("test", result.getUserName());
    }

    @Test
    void whenGetUserByUserNameWithControlCalledWithInvalidId_itShouldThrowNotFoundException() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.getUserByUserNameWithControl("test"));

        assertEquals(UserErrorMessage.USER_NOT_FOUND_USERNAME, exception.getBaseErrorMessage());
    }

    @Test
    void whenUpdateUserByIdtCalledWithValidId_itShouldReturnUserDto() {
        User user = new User();
        user.setId(1L);

        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setUserName("test");

        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setId(1L);

        when(userDao.findById(1L)).thenReturn(Optional.of(user));
        when(userDao.existsAllByUserName(userUpdateDto.getUserName())).thenReturn(false);
        when(userDao.save(user)).thenReturn(user);
        when(mapper.userUpdateDtoToUser(userUpdateDto)).thenReturn(user);
        when(mapper.userToUserGetDto(user)).thenReturn(userGetDto);

        UserGetDto result = userService.updateUserById(1L, userUpdateDto);

        verify(userDao).findById(1L);
        verify(userDao).save(user);
        verify(mapper).userUpdateDtoToUser(userUpdateDto);
        verify(mapper).userToUserGetDto(user);

        assertEquals(1L, result.getId());
    }

    @Test
    void whenUpdateUserByIdCalledWithInvalidId_itShouldThrowNotFoundException() {

        UserUpdateDto userUpdateDto = mock(UserUpdateDto.class);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.updateUserById(null, userUpdateDto));

        assertEquals(UserErrorMessage.USER_NOT_FOUND_ID, exception.getBaseErrorMessage());
    }

    @Test
    void whenDeleteUserByIdCalledWithValidId_itShouldDeleteUser() {
        User user = mock(User.class);

        when(userDao.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUserById(1L);

        verify(userDao).findById(1L);
        verify(userDao).delete(user);
    }

    @Test
    void whenDeleteUserByIdCalledWithInvalidId_itShouldThrowNotFoundException() {

        when(userDao.findById(1L)).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> userService.deleteUserById(1L));

        verify(userDao).findById(1L);
    }
}
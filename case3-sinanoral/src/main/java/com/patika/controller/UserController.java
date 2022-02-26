package com.patika.controller;

import com.patika.model.requestDto.UserCreateDto;
import com.patika.model.requestDto.UserUpdateDto;
import com.patika.model.responseDto.UserGetDto;
import com.patika.service.UserService;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public DataResult<List<UserGetDto>> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<UserGetDto> getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/username/{userName}")
    public DataResult<UserGetDto> getByName(@PathVariable String userName) {
        return userService.getByName(userName);
    }

    @PostMapping()
    public Result create(@RequestBody UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    @DeleteMapping()
    public Result delete(@RequestParam String userName, @RequestParam String phoneNumber) {
        return userService.deleteByUserNameAndPhoneNumber(userName, phoneNumber);
    }

    @PutMapping("/{id}")
    public Result updateUserById(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUserById(id, userUpdateDto);
    }
}

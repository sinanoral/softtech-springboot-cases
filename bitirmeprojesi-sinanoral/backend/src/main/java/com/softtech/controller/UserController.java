package com.softtech.controller;

import com.softtech.model.requestDto.UserUpdateDto;
import com.softtech.model.responseDto.RestResponse;
import com.softtech.model.responseDto.UserGetDto;
import com.softtech.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @Operation(tags = "User Controller")
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserGetDto>> updateUserById(@PathVariable @Min(1) Long id,
                                                                   @RequestBody @Valid UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(RestResponse.of(userService.updateUserById(id, userUpdateDto)));
    }

    @Operation(tags = "User Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteUserById(@PathVariable @Min(1) Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(RestResponse.empty("User deleted!"));
    }
}

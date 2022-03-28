package com.softtech.controller;

import com.softtech.model.requestDto.LoginRequestDto;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.model.responseDto.LoginResponseDto;
import com.softtech.model.responseDto.RestResponse;
import com.softtech.model.responseDto.UserGetDto;
import com.softtech.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(tags = "Authentication Controller")
    @PostMapping("/login")
    public ResponseEntity<RestResponse<LoginResponseDto>> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(RestResponse.of(authenticationService.login(loginRequestDto)));
    }

    @Operation(tags = "Authentication Controller")
    @PostMapping("/register")
    public ResponseEntity<RestResponse<UserGetDto>> register(@RequestBody @Valid UserCreateDto userCreateDto) {
        return ResponseEntity.ok(RestResponse.of(authenticationService.register(userCreateDto)));
    }
}

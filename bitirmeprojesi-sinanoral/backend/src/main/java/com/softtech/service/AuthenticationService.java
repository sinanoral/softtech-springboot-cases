package com.softtech.service;

import com.softtech.model.requestDto.LoginRequestDto;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.model.responseDto.LoginResponseDto;
import com.softtech.model.responseDto.UserGetDto;
import com.softtech.security.enums.EnumJwtConstant;
import com.softtech.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserGetDto register(UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        String bearer = EnumJwtConstant.BEARER.getConstant();

        return new LoginResponseDto(bearer + token);
    }
}

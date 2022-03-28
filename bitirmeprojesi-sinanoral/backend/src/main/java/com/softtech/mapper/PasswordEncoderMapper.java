package com.softtech.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {

    protected final PasswordEncoder passwordEncoder;

    @EncodedMapping
    public String encode(String value) {
        return passwordEncoder.encode(value);
    }
}

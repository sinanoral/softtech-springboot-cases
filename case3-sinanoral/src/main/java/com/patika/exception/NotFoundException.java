package com.patika.exception;

import com.patika.enums.errors.BaseErrorMessage;

import java.util.NoSuchElementException;

public class NotFoundException extends NoSuchElementException {
    public NotFoundException(BaseErrorMessage message) {
        super(message.getMessage());
    }
}

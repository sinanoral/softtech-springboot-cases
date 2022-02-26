package com.patika.exception;

import com.patika.enums.errors.ErrorMessage;

public class NoSuchElementFoundException extends RuntimeException {
    public NoSuchElementFoundException(ErrorMessage message){
        super(message.getMessage());
    }
}

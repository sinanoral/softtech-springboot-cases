package com.softtech.exceptions;

import com.softtech.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEntityException extends BusinessException {
    public DuplicateEntityException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
package com.softtech.softtechspringboot.app.gen.exceptions;

import com.softtech.softtechspringboot.app.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends GenBusinessException {

    public ItemNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

    public ItemNotFoundException(BaseErrorMessage message, String entityName) {
        super(message, entityName);
    }
}

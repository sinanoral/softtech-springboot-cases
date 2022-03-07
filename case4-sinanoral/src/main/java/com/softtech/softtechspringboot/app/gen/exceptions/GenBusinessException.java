package com.softtech.softtechspringboot.app.gen.exceptions;

import com.softtech.softtechspringboot.app.gen.enums.BaseErrorMessage;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Data
public class GenBusinessException extends RuntimeException {

    private final BaseErrorMessage baseErrorMessage;
    private String entityName;

    public GenBusinessException(BaseErrorMessage baseErrorMessage, String entityName) {
        this.baseErrorMessage = baseErrorMessage;
        this.entityName = entityName;
    }

    public GenBusinessException(BaseErrorMessage baseErrorMessage) {
        this.baseErrorMessage = baseErrorMessage;
    }
}

package com.softtech.exception;

import com.softtech.exception.response.ErrorResponse;
import com.softtech.exceptions.DuplicateEntityException;
import com.softtech.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {

        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, description);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {

        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), message, detailMessage);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDuplicateEntityExceptions(DuplicateEntityException ex) {

        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), message, detailMessage);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = "Validation failed!";
        String detailMessage = "There are validation errors!";

        ErrorResponse errorResponse = new ErrorResponse(status.value(), message, detailMessage);
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errorResponse.addValidationError(((FieldError) error).getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}


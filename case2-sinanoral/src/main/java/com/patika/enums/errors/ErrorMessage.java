package com.patika.enums.errors;

public enum ErrorMessage {
    ITEM_NOT_FOUND("Item not found"),
    ;
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}

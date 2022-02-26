package com.patika.enums.errors;

public enum UserErrorMessage implements BaseErrorMessage {
    USER_NOT_FOUND("User not found!"),
    USERNAME_AND_PHONE_NOT_MATCH("Username and phone do not match!");

    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

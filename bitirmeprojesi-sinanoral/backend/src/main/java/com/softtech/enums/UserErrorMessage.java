package com.softtech.enums;

public enum UserErrorMessage implements BaseErrorMessage {
    USER_NOT_FOUND_USERNAME("Entities Could Not Found", "There are not any saved user with this username."),
    USER_NOT_FOUND_ID("Entities Could Not Found", "There are not any saved user with this id."),
    HAS_DUPLICATE_USER_USERNAME("Encounter A Conflict", "This name is already used for another user."),
    ;

    private final String message;
    private final String detailMessage;

    UserErrorMessage(String message, String detailMessage) {
        this.message = message;
        this.detailMessage = detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetailMessage() {
        return detailMessage;
    }
}

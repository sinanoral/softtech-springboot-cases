package com.softtech.enums;

public enum ProductErrorMessage implements BaseErrorMessage {
    PRODUCT_NOT_FOUND_ID("Entities Could Not Found", "There are not any saved product with this id."),
    ;

    private final String message;
    private final String detailMessage;

    ProductErrorMessage(String message, String detailMessage) {
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

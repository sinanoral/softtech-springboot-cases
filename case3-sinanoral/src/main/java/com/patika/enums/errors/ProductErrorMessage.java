package com.patika.enums.errors;

public enum ProductErrorMessage implements BaseErrorMessage {
    PRODUCT_NOT_FOUND("Product not found!"),
    ;

    private final String message;

    ProductErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

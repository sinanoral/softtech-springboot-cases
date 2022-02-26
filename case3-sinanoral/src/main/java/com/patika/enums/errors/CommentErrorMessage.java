package com.patika.enums.errors;

public enum CommentErrorMessage implements BaseErrorMessage {
    COMMENT_NOT_FOUND("Comment not found!"),
    USER_COMMENTS_NOT_FOUND("User has no comments!"),
    PRODUCT_COMMENTS_NOT_FOUND("There are no comments for the product!");

    private final String message;

    CommentErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
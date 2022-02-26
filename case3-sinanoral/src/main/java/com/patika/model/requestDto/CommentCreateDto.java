package com.patika.model.requestDto;

import lombok.Data;

@Data
public class CommentCreateDto {
    private String context;
    private Long productId;
    private Long userId;
}

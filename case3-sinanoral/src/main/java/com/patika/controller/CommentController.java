package com.patika.controller;

import com.patika.model.requestDto.CommentCreateDto;
import com.patika.model.responseDto.CommentProductGetDto;
import com.patika.model.responseDto.CommentUserGetDto;
import com.patika.service.CommentService;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/users/{id}")
    public DataResult<List<CommentUserGetDto>> getCommentsByUserId(@PathVariable Long id) {
        return commentService.getCommentsByUserId(id);
    }

    @GetMapping("/products/{id}")
    public DataResult<List<CommentProductGetDto>> getCommentsByProductId(@PathVariable Long id) {
        return commentService.getCommentsByProductId(id);
    }

    @PostMapping()
    public Result create(@RequestBody CommentCreateDto commentCreateDto) {
        return commentService.create(commentCreateDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return commentService.deleteById(id);
    }
}

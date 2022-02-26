package com.patika.mapper;

import com.patika.model.entity.Comment;
import com.patika.model.requestDto.CommentCreateDto;
import com.patika.model.responseDto.CommentProductGetDto;
import com.patika.model.responseDto.CommentUserGetDto;
import com.patika.service.ProductService;
import com.patika.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserService.class, ProductService.class})
public interface CommentMapper {

    @Mapping(source = "user.id", target = "userId")
    CommentProductGetDto commentToCommentProductGetDto(Comment comment);

    @Mapping(source = "product.id", target = "productId")
    CommentUserGetDto commentToCommentUserGetDto(Comment comment);

    List<CommentProductGetDto> commentListToCommentProductGetDtoList(List<Comment> comments);

    List<CommentUserGetDto> commentListToCommentUserGetDtoList(List<Comment> comments);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "productId", target = "product")
    Comment commentCreateDtoToComment(CommentCreateDto commentCreateDto);

}

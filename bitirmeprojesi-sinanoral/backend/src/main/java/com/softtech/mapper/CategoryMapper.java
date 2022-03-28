package com.softtech.mapper;

import com.softtech.model.entity.Category;
import com.softtech.model.responseDto.CategoryGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    List<CategoryGetDto> categoryListToCategoryGetDtoList(List<Category> categories);

    CategoryGetDto categoryToCategoryGetDto(Category category);
}

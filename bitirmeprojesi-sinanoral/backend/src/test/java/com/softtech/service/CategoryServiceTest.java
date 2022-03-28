package com.softtech.service;

import com.softtech.dao.CategoryDao;
import com.softtech.enums.CategoryErrorMessage;
import com.softtech.exceptions.EntityNotFoundException;
import com.softtech.mapper.CategoryMapper;
import com.softtech.model.entity.Category;
import com.softtech.model.responseDto.CategoryDetailDto;
import com.softtech.model.responseDto.CategoryGetDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private CategoryMapper mapper;

    @Test
    void whenGetAllCategoriesCalled_itShouldReturnCategoryDtoList() {
        Category category = mock(Category.class);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        CategoryGetDto categoryGetDto = mock(CategoryGetDto.class);
        List<CategoryGetDto> categoryGetDtoList = new ArrayList<>();
        categoryGetDtoList.add(categoryGetDto);

        when(categoryDao.findAll()).thenReturn(categoryList);
        when(mapper.categoryListToCategoryGetDtoList(categoryList)).thenReturn(categoryGetDtoList);

        List<CategoryGetDto> result = categoryService.getAllCategories();

        verify(categoryDao).findAll();
        verify(mapper).categoryListToCategoryGetDtoList(categoryList);

        assertEquals(1, result.size());
    }

    @Test
    void whenGetCategoriesInformationCalled_isShouldReturnCategoryDetailsDtoList() {
        CategoryDetailDto categoryDetailDto = mock(CategoryDetailDto.class);
        List<CategoryDetailDto> categoryDetailDtoList = new ArrayList<>();
        categoryDetailDtoList.add(categoryDetailDto);

        when(categoryDao.getCategoriesInformation()).thenReturn(categoryDetailDtoList);

        List<CategoryDetailDto> result = categoryService.getCategoriesInformation();

        verify(categoryDao).getCategoriesInformation();

        assertEquals(1, result.size());
    }

    @Test
    void whenGetCategoryByIdWithControlCalledWithValidId_itShouldReturnCategory() {

        Category category = new Category();
        category.setId(1L);

        when(categoryDao.findById(1L)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryByIdWithControl(1L);

        verify(categoryDao).findById(1L);

        assertEquals(1L, result.getId());
    }

//    @Test
//    void whenUpdateCategoryVatRateByIdCalledWithValidIdAndUpdateDto_itShouldReturnCategoryDto() {
//        CategoryUpdateDto categoryUpdateDto = new CategoryUpdateDto();
//        categoryUpdateDto.setVatRate(BigDecimal.valueOf(1));
//        Long id = 1L;
//
//        Category category = new Category();
//        category.setVatRate(BigDecimal.valueOf(1));
//        category.setName("test");
//        category.setId(id);
//
//        when(categoryDao.findById(id)).thenReturn(Optional.of(category));
//        when(categoryDao.save(category)).thenReturn(category);
//        doNothing().when(productService).updateProductsPriceWithUpdatedCategory(category);
//
//        CategoryGetDto result = categoryService.updateCategoryVatRateById(id, categoryUpdateDto);
//
//        assertNotNull(result);
//    }

    @Test
    void whenGetCategoryByIdWithControlCalledWithInvalidId_itShouldThrowNotFoundException() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> categoryService.getCategoryByIdWithControl(1L));

        assertEquals(CategoryErrorMessage.CATEGORY_NOT_FOUND_ID, exception.getBaseErrorMessage());
    }
}
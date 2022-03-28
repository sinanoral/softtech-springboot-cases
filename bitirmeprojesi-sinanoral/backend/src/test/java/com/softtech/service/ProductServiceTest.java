package com.softtech.service;

import com.softtech.dao.ProductDao;
import com.softtech.enums.CategoryErrorMessage;
import com.softtech.enums.ProductErrorMessage;
import com.softtech.exceptions.EntityNotFoundException;
import com.softtech.mapper.ProductMapper;
import com.softtech.model.entity.Category;
import com.softtech.model.entity.Product;
import com.softtech.model.requestDto.ProductCreateDto;
import com.softtech.model.requestDto.ProductUpdateDto;
import com.softtech.model.responseDto.ProductGetDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductDao productDao;

    @Mock
    private ProductMapper mapper;

    @Test
    void whenGetAllProductsCalled_itShouldReturnProductDtoList() {

        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        ProductGetDto productGetDto = mock(ProductGetDto.class);
        List<ProductGetDto> productGetDtoList = new ArrayList<>();
        productGetDtoList.add(productGetDto);

        when(productDao.findAll()).thenReturn(productList);
        when(mapper.productListToProductGetDtoList(productList)).thenReturn(productGetDtoList);

        List<ProductGetDto> result = productService.getAllProducts();

        verify(productDao).findAll();
        verify(mapper).productListToProductGetDtoList(productList);

        assertEquals(1, result.size());
    }

    @Test
    void whenGetAllProductsCalledWithParameters_itShouldReturnProductDtoList() {

        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        product1.setVatInclusivePrice(BigDecimal.valueOf(40));
        product2.setVatInclusivePrice(BigDecimal.valueOf(50));

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        ProductGetDto productGetDto1 = mock(ProductGetDto.class);
        ProductGetDto productGetDto2 = mock(ProductGetDto.class);
        productGetDto1.setVatInclusivePrice(BigDecimal.valueOf(40));
        productGetDto2.setVatInclusivePrice(BigDecimal.valueOf(50));

        List<ProductGetDto> productGetDtoList = new ArrayList<>();
        productGetDtoList.add(productGetDto1);
        productGetDtoList.add(productGetDto2);

        BigDecimal min = BigDecimal.valueOf(30);
        BigDecimal max = BigDecimal.valueOf(60);
        when(productDao.getAllByVatInclusivePriceBetween(min, max))
                .thenReturn(productList);

        when(mapper.productListToProductGetDtoList(productList)).thenReturn(productGetDtoList);

        List<ProductGetDto> result = productService.getAllProducts(min, max);

        verify(productDao).getAllByVatInclusivePriceBetween(min, max);
        verify(mapper).productListToProductGetDtoList(productList);

        assertEquals(2, result.size());
    }

    @Test
    void whenGetProductByIdCalledWithValidId_itShouldReturnProductDto() {
        Product product = mock(Product.class);

        ProductGetDto productGetDto = new ProductGetDto();
        productGetDto.setId(1L);

        when(productDao.findById(1L)).thenReturn(Optional.of(product));
        when(mapper.productToProductGetDto(any())).thenReturn(productGetDto);

        ProductGetDto result = productService.getProductById(1L);

        verify(productDao).findById(1L);
        verify(mapper).productToProductGetDto(product);

        assertEquals(1L, result.getId());
    }

    @Test
    void whenGetProductByIdCalledWithInvalidId_itShouldThrowNotFoundException() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> productService.getProductById(null));

        assertEquals(ProductErrorMessage.PRODUCT_NOT_FOUND_ID, exception.getBaseErrorMessage());
    }

    @Test
    void whenGetAllProductsByCategoryIdCalledWithValidId_itShouldReturnProductDtoList() {
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        ProductGetDto productGetDto = mock(ProductGetDto.class);
        List<ProductGetDto> productGetDtoList = new ArrayList<>();
        productGetDtoList.add(productGetDto);

        when(productDao.getAllByCategory_Id(1L)).thenReturn(productList);
        when(mapper.productListToProductGetDtoList(productList)).thenReturn(productGetDtoList);

        List<ProductGetDto> result = productService.getAllProductsByCategoryId(1L);

        verify(productDao).getAllByCategory_Id(1L);
        verify(mapper).productListToProductGetDtoList(productList);

        assertEquals(1, result.size());
    }

    @Test
    void whenGetAllProductsByCategoryIdCalledWithInvalidId_itShouldThrowNotFoundException() {

        when(categoryService.getCategoryByIdWithControl(null))
                .thenThrow(new EntityNotFoundException(CategoryErrorMessage.CATEGORY_NOT_FOUND_ID));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> productService.getAllProductsByCategoryId(null));

        assertEquals(CategoryErrorMessage.CATEGORY_NOT_FOUND_ID, exception.getBaseErrorMessage());
    }

    @Test
    void whenCreateProductCalledWithValidDto_itShouldReturnProductDto() {
        Category category = new Category();
        category.setId(1L);
        category.setVatRate(BigDecimal.valueOf(0.1));

        Product product = new Product();
        product.setCategory(category);
        product.setPrice(BigDecimal.valueOf(10));

        ProductCreateDto productCreateDto = mock(ProductCreateDto.class);
        ProductGetDto productGetDto = mock(ProductGetDto.class);

        when(productGetDto.getId()).thenReturn(1L);
        when(categoryService.getCategoryByIdWithControl(1L)).thenReturn(category);
        when(mapper.productCreateDtoProduct(productCreateDto)).thenReturn(product);
        when(productDao.save(product)).thenReturn(product);
        when(mapper.productToProductGetDto(product)).thenReturn(productGetDto);

        ProductGetDto result = productService.createProduct(productCreateDto);

        verify(mapper).productCreateDtoProduct(productCreateDto);
        verify(productDao).save(product);
        verify(mapper).productToProductGetDto(product);

        assertEquals(1L, result.getId());
    }

    @Test
    void whenCreateProductCalledWithNullParameter_itShouldThrowException() {
        assertThrows(NullPointerException.class, () -> productService.createProduct(null));
    }

    @Test
    void whenUpdateProductByIdtCalledWithValidId_itShouldReturnProductDto() {
        Category category = new Category();
        category.setId(1L);
        category.setVatRate(BigDecimal.valueOf(0.1));

        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(10));

        ProductUpdateDto productUpdateDto = new ProductUpdateDto();
        productUpdateDto.setPrice(BigDecimal.valueOf(10));
        productUpdateDto.setName("test");
        productUpdateDto.setCategoryId(1L);

        ProductGetDto productGetDto = new ProductGetDto();
        productGetDto.setId(1L);

        when(productDao.findById(1L)).thenReturn(Optional.of(product));
        when(categoryService.getCategoryByIdWithControl(1L)).thenReturn(category);
        when(productDao.save(product)).thenReturn(product);
        when(mapper.productToProductGetDto(product)).thenReturn(productGetDto);

        ProductGetDto result = productService.updateProductById(1L, productUpdateDto);

        verify(productDao).findById(1L);
        verify(productDao).save(product);
        verify(mapper).productToProductGetDto(product);

        assertEquals(1L, result.getId());
    }

    @Test
    void whenUpdateProductByIdCalledWithInvalidId_itShouldThrowNotFoundException() {

        ProductUpdateDto productUpdateDto = mock(ProductUpdateDto.class);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> productService.updateProductById(null, productUpdateDto));

        assertEquals(ProductErrorMessage.PRODUCT_NOT_FOUND_ID, exception.getBaseErrorMessage());
    }

    @Test
    void whenUpdateProductPriceByIdWithValidIdAndPrice_itShouldReturnProductDto() {
        Category category = new Category();
        category.setId(1L);
        category.setVatRate(BigDecimal.valueOf(0.1));

        Product product = new Product();
        product.setCategory(category);
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(10));

        ProductGetDto productGetDto = mock(ProductGetDto.class);

        when(productGetDto.getPrice()).thenReturn(BigDecimal.valueOf(10));
        when(productDao.findById(1L)).thenReturn(Optional.of(product));
        when(categoryService.getCategoryByIdWithControl(1L)).thenReturn(category);
        when(productDao.save(product)).thenReturn(product);
        when(mapper.productToProductGetDto(product)).thenReturn(productGetDto);

        ProductGetDto result = productService.updateProductPriceById(1L, BigDecimal.valueOf(10));

        verify(productDao).findById(1L);
        verify(productDao).save(product);
        verify(mapper).productToProductGetDto(product);

        assertEquals(BigDecimal.valueOf(10), result.getPrice());
    }

    @Test
    void whenUpdateProductPriceByIdWithInvalidPrice_itShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> productService.updateProductPriceById(1L, BigDecimal.ZERO));
    }

    @Test
    void whenDeleteProductByIdCalledWithValidId_itShouldDeleteProduct() {
        Product product = mock(Product.class);

        when(productDao.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProductById(1L);

        verify(productDao).findById(1L);
        verify(productDao).delete(product);
    }

    @Test
    void whenDeleteProductByIdCalledWithInvalidId_itShouldThrowNotFoundException() {

        when(productDao.findById(1L)).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> productService.deleteProductById(1L));

        verify(productDao).findById(1L);
    }
}
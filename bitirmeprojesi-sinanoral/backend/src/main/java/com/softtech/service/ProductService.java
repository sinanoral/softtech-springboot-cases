package com.softtech.service;

import com.softtech.dao.ProductDao;
import com.softtech.enums.ProductErrorMessage;
import com.softtech.exceptions.EntityNotFoundException;
import com.softtech.mapper.ProductMapper;
import com.softtech.model.entity.Category;
import com.softtech.model.entity.Product;
import com.softtech.model.requestDto.ProductCreateDto;
import com.softtech.model.requestDto.ProductUpdateDto;
import com.softtech.model.responseDto.ProductGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final ProductMapper mapper;

    private final CategoryService categoryService;

    public List<ProductGetDto> getAllProducts() {
        List<Product> products = productDao.findAll();
        return mapper.productListToProductGetDtoList(products);
    }

    public List<ProductGetDto> getAllProducts(BigDecimal min, BigDecimal max) {
        List<Product> products = productDao.getAllByVatInclusivePriceBetween(min, max);
        return mapper.productListToProductGetDtoList(products);
    }

    public ProductGetDto getProductById(Long id) {
        Product product = getProductByIdWithControl(id);
        return mapper.productToProductGetDto(product);
    }

    public List<ProductGetDto> getAllProductsByCategoryId(Long id) {
        categoryService.getCategoryByIdWithControl(id);
        List<Product> products = productDao.getAllByCategory_Id(id);
        return mapper.productListToProductGetDtoList(products);
    }

    public ProductGetDto createProduct(ProductCreateDto productCreateDto) {
        Product product = mapper.productCreateDtoProduct(productCreateDto);
        setVatInclusivePriceAndVatAmount(product);
        productDao.save(product);
        return mapper.productToProductGetDto(product);
    }

    public ProductGetDto updateProductById(Long id, ProductUpdateDto productUpdateDto) {
        Product product = getProductByIdWithControl(id);
        product.setName(productUpdateDto.getName());
        product.setCategory(categoryService.getCategoryByIdWithControl(productUpdateDto.getCategoryId()));
        product.setPrice(productUpdateDto.getPrice());
        setVatInclusivePriceAndVatAmount(product);
        productDao.save(product);
        return mapper.productToProductGetDto(product);
    }

    public ProductGetDto updateProductPriceById(Long id, BigDecimal price) {
        validatePrice(price);
        Product product = getProductByIdWithControl(id);
        product.setPrice(price);
        setVatInclusivePriceAndVatAmount(product);
        productDao.save(product);
        return mapper.productToProductGetDto(product);
    }

    public void deleteProductById(Long id) {
        Product product = getProductByIdWithControl(id);
        productDao.delete(product);
    }

    @Transactional
    public void updateProductsPriceWithUpdatedCategory(Category category) {

        List<Product> products = productDao.getAllByCategory_Id(category.getId())
                .stream()
                .peek(this::setVatInclusivePriceAndVatAmount)
                .collect(Collectors.toList());

        productDao.saveAll(products);
    }

    private void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(0.0001)) < 0)
            throw new IllegalArgumentException();
    }

    private Product getProductByIdWithControl(Long id) {
        return productDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException(ProductErrorMessage.PRODUCT_NOT_FOUND_ID));
    }

    private void setVatInclusivePriceAndVatAmount(Product product) {
        BigDecimal vatRate = categoryService.getCategoryByIdWithControl(product.getCategory().getId()).getVatRate();
        BigDecimal taxFreePrice = product.getPrice();

        product.setVatInclusivePrice(taxFreePrice.multiply(vatRate).add(taxFreePrice));
        product.setVatAmount(product.getVatInclusivePrice().subtract(taxFreePrice));
    }
}

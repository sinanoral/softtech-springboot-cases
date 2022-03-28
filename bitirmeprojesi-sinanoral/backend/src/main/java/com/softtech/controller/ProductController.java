package com.softtech.controller;

import com.softtech.model.requestDto.ProductCreateDto;
import com.softtech.model.requestDto.ProductUpdateDto;
import com.softtech.model.responseDto.ProductGetDto;
import com.softtech.model.responseDto.RestResponse;
import com.softtech.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

    private final ProductService productService;

    @Operation(tags = "Product Controller")
    @GetMapping()
    public ResponseEntity<RestResponse<List<ProductGetDto>>> getAllProducts() {
        return ResponseEntity.ok(RestResponse.of(productService.getAllProducts()));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/between")
    public ResponseEntity<RestResponse<List<ProductGetDto>>> getAllProducts(@RequestParam BigDecimal min,
                                                                            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(RestResponse.of(productService.getAllProducts(min, max)));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ProductGetDto>> getProductById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(RestResponse.of(productService.getProductById(id)));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<RestResponse<List<ProductGetDto>>> getAllProductsByCategoryId(@PathVariable @Min(1) Long categoryId) {
        return ResponseEntity.ok(RestResponse.of(productService.getAllProductsByCategoryId(categoryId)));
    }

    @Operation(
            tags = "Product Controller",
            description = "Creates new product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Customers",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ProductCreateDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Create product",
                                                    summary = "Create product request example",
                                                    description = "Complete request with all available fields to create product",
                                                    value = "{\n" +
                                                            "  \"name\": \"pizza\",\n" +
                                                            "  \"categoryId\": 1,\n" +
                                                            "  \"price\": 50" +
                                                            "}"
                                            ),
                                    }
                            ),
                    }
            )
    )
    @PostMapping()
    public ResponseEntity<RestResponse<MappingJacksonValue>> createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {

        ProductGetDto productGetDto = productService.createProduct(productCreateDto);

        WebMvcLinkBuilder linkGet = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        this.getClass()).getProductById(productGetDto.getId()));

        WebMvcLinkBuilder linkDelete = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        this.getClass()).deleteProductById(productGetDto.getId()));

        EntityModel<ProductGetDto> entityModel = EntityModel.of(productGetDto);

        entityModel.add(linkGet.withRel("get-by-id"));
        entityModel.add(linkDelete.withRel("delete"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);

        return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));
    }

    @Operation(
            tags = "Product Controller",
            description = "Updates product by id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Customers",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ProductUpdateDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Update product by id",
                                                    summary = "Update product by id request example",
                                                    description = "Complete request with all available fields to update product",
                                                    value = "{\n" +
                                                            "  \"name\": \"hamburger\",\n" +
                                                            "  \"categoryId\": 1,\n" +
                                                            "  \"price\": 35" +
                                                            "}"
                                            ),
                                    }
                            ),
                    }
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<ProductGetDto>> updateProductById(@PathVariable @Min(1) Long id,
                                                                         @RequestBody @Valid ProductUpdateDto productUpdateDto) {
        return ResponseEntity.ok(RestResponse.of(productService.updateProductById(id, productUpdateDto)));
    }

    @Operation(tags = "Product Controller")
    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<ProductGetDto>> updateProductPriceById(@PathVariable @Min(1) Long id,
                                                                              @RequestParam BigDecimal price) {
        return ResponseEntity.ok(RestResponse.of(productService.updateProductPriceById(id, price)));
    }

    @Operation(tags = "Product Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteProductById(@PathVariable @Min(1) Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok(RestResponse.empty());
    }
}

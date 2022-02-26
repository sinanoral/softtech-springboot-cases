package com.patika.controller;

import com.patika.model.requestDto.ProductCreateDto;
import com.patika.model.requestDto.ProductUpdateDto;
import com.patika.model.responseDto.ProductGetDto;
import com.patika.service.ProductService;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public DataResult<List<ProductGetDto>> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<ProductGetDto> getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping()
    public Result create(@RequestBody ProductCreateDto productCreateDto) {
        return productService.create(productCreateDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Result updatePriceById(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto) {
        return productService.updatePriceById(id, productUpdateDto);
    }
}

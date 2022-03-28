package com.softtech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.BaseTest;
import com.softtech.model.requestDto.ProductCreateDto;
import com.softtech.model.requestDto.ProductUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Sql({"/import_categories.sql", "/import_products.sql"})
class ProductControllerTest extends BaseTest {
    private static final String BASE_PATH = "/api/v1/products";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void getAllProducts() throws Exception {
        ProductCreateDto productCreateDto = ProductCreateDto.builder()
                .name("test")
                .categoryId(1L)
                .price(BigDecimal.valueOf(10))
                .build();

        String content = objectMapper.writeValueAsString(productCreateDto);

        mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void getProductById() throws Exception {
        ProductCreateDto productCreateDto = ProductCreateDto.builder()
                .name("test")
                .categoryId(1L)
                .price(BigDecimal.valueOf(10))
                .build();

        String content = objectMapper.writeValueAsString(productCreateDto);

        mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/1").content("1").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void getAllProductsByCategoryId() throws Exception {
        ProductCreateDto productCreateDto = ProductCreateDto.builder()
                .name("test")
                .categoryId(1L)
                .price(BigDecimal.valueOf(10))
                .build();

        String content = objectMapper.writeValueAsString(productCreateDto);

        mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/categories/1").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void createProduct() throws Exception {
        ProductCreateDto productCreateDto = ProductCreateDto.builder()
                .name("test")
                .categoryId(1L)
                .price(BigDecimal.valueOf(10))
                .build();

        String content = objectMapper.writeValueAsString(productCreateDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updateProductById() throws Exception {
        ProductUpdateDto productUpdateDto = new ProductUpdateDto();
        productUpdateDto.setName("test1");
        productUpdateDto.setCategoryId(2L);
        productUpdateDto.setPrice(BigDecimal.valueOf(20));

        String content = objectMapper.writeValueAsString(productUpdateDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH + "/1").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updateProductPriceById() throws Exception {
        ProductCreateDto productCreateDto = ProductCreateDto.builder()
                .name("test")
                .categoryId(1L)
                .price(BigDecimal.valueOf(10))
                .build();

        String content = objectMapper.writeValueAsString(productCreateDto);

        mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        MvcResult result = mockMvc.perform(
                patch(BASE_PATH + "/1").param("price", "15").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void deleteProductById() throws Exception {
        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/2").content("2").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}
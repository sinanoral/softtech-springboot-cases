package com.patika.model.request;

import lombok.Data;

@Data
public class CreateCountryRequest {
    private String name;
    private String code;
}

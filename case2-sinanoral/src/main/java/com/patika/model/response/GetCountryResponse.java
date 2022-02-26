package com.patika.model.response;

import lombok.Data;

@Data
public class GetCountryResponse {
    private Long id;
    private String name;
    private String code;
}

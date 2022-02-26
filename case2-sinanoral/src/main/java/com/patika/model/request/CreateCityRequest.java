package com.patika.model.request;

import lombok.Data;

@Data
public class CreateCityRequest {
    private String name;
    private String plateNo;
    private Long countryId;
}

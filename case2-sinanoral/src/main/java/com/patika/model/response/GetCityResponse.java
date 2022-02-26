package com.patika.model.response;

import lombok.Data;

@Data
public class GetCityResponse {
    private Long id;
    private String name;
    private String plateNo;
}

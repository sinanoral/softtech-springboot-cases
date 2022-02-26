package com.patika.model.request;

import lombok.Data;

@Data
public class CreateDistrictRequest {
    private String name;
    private Long cityId;
}

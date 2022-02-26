package com.patika.model.request;

import lombok.Data;

@Data
public class CreateNeighborhoodRequest {
    private String name;
    private Long districtId;
}

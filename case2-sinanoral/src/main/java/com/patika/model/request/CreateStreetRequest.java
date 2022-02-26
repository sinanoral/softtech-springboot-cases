package com.patika.model.request;

import lombok.Data;

@Data
public class CreateStreetRequest {
    private String name;
    private Long NeighborhoodId;
}

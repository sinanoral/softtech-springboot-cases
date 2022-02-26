package com.patika.model.request;

import lombok.Data;

@Data
public class CreateAddressRequest {
    private Long countryId;
    private Long cityId;
    private Long districtId;
    private Long neighborhoodId;
    private Long streetId;
    private String buildingNo;
    private String apartmentNo;
}

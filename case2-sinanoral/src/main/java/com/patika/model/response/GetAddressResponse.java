package com.patika.model.response;

import com.patika.model.entity.*;
import lombok.Data;

@Data
public class GetAddressResponse {
    private Long id;
    private Country country;
    private City city;
    private District district;
    private Neighborhood neighborhood;
    private Street street;
    private String buildingNo;
    private String apartmentNo;
}

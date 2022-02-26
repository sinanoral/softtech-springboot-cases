package com.patika.mapper;

import com.patika.model.entity.Country;
import com.patika.model.request.CreateCountryRequest;
import com.patika.model.response.GetCountryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper {
    GetCountryResponse countryToGetCountryResponse(Country country);

    Country createCountryRequestToCountry(CreateCountryRequest createCountryRequest);
}

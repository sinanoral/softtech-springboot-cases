package com.patika.mapper;

import com.patika.model.entity.City;
import com.patika.model.request.CreateCityRequest;
import com.patika.model.response.GetCityResponse;
import com.patika.service.CountryService;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CityMapper {

    @Autowired
    private CountryService countryService;

    public City createCityRequestToCity(CreateCityRequest createCityRequest) {

        if (createCityRequest == null) {
            return null;
        }

        City city = new City();
        city.setName(createCityRequest.getName());
        city.setPlateNo(createCityRequest.getPlateNo());
        city.setCountry(countryService.getById(createCityRequest.getCountryId()));

        return city;
    }

    public abstract GetCityResponse countryToGetCountryResponse(City city);
}

package com.patika.mapper;

import com.patika.model.entity.*;
import com.patika.model.request.CreateAddressRequest;
import com.patika.model.response.GetAddressResponse;
import com.patika.service.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AddressMapper {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private NeighborhoodService neighborhoodService;

    @Autowired
    private StreetService streetService;

    @Autowired
    private DistrictService districtService;

    public abstract GetAddressResponse addressToGetAddressResponse(Address address);

    public abstract List<GetAddressResponse> addressListToGetAddressResponseList(List<Address> addresses);

    @Mappings({
            @Mapping(target = "country", source = "countryId"),
            @Mapping(target = "city", source = "cityId"),
            @Mapping(target = "district", source = "districtId"),
            @Mapping(target = "neighborhood", source = "neighborhoodId"),
            @Mapping(target = "street", source = "streetId"),
    })
    public abstract Address createAddressRequestToAddress(CreateAddressRequest createAddressRequest);

    protected Country LongToCountry(Long countryId) throws EntityNotFoundException {
        return countryService.getById(countryId);
    }

    protected City LongToCity(Long cityId) throws EntityNotFoundException {
        return cityService.getById(cityId);
    }

    protected District LongToDistrict(Long districtId) throws EntityNotFoundException {
        return districtService.getById(districtId);
    }

    protected Neighborhood LongToNeighborhood(Long neighborhoodId) throws EntityNotFoundException {
        return neighborhoodService.getById(neighborhoodId);
    }

    protected Street LongToStreet(Long streetId) throws EntityNotFoundException {
        return streetService.getById(streetId);
    }
}

package com.patika.mapper;

import com.patika.model.entity.Street;
import com.patika.model.request.CreateStreetRequest;
import com.patika.model.response.GetStreetResponse;
import com.patika.service.NeighborhoodService;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StreetMapper {

    @Autowired
    private NeighborhoodService neighborhoodService;

    public Street createStreetRequestToStreet(CreateStreetRequest createStreetRequest) {

        if (createStreetRequest == null) {
            return null;
        }

        Street street = new Street();
        street.setName(createStreetRequest.getName());
        street.setNeighborhood(neighborhoodService.getById(createStreetRequest.getNeighborhoodId()));

        return street;
    }

    public abstract List<GetStreetResponse> streetListToGetStreetResponseList(List<Street> streets);
}

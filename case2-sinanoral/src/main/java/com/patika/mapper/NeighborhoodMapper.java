package com.patika.mapper;

import com.patika.model.entity.Neighborhood;
import com.patika.model.request.CreateNeighborhoodRequest;
import com.patika.model.response.GetNeighborhoodResponse;
import com.patika.service.DistrictService;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NeighborhoodMapper {

    @Autowired
    private DistrictService districtService;

    public Neighborhood createNeighborhoodRequestToNeighborhood(CreateNeighborhoodRequest createNeighborhoodRequest) {
        if (createNeighborhoodRequest == null) {
            return null;
        }

        Neighborhood neighborhood = new Neighborhood();
        neighborhood.setName(createNeighborhoodRequest.getName());
        neighborhood.setDistrict(districtService.getById(createNeighborhoodRequest.getDistrictId()));

        return neighborhood;
    }

    public abstract List<GetNeighborhoodResponse> neighborhoodListToGetNeighborhoodResponseList(List<Neighborhood> neighborhoods);
}

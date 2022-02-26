package com.patika.mapper;

import com.patika.model.entity.District;
import com.patika.model.request.CreateDistrictRequest;
import com.patika.model.response.GetDistrictResponse;
import com.patika.service.CityService;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DistrictMapper {

    @Autowired
    private CityService cityService;

    public District createDistrictRequestToDistrict(CreateDistrictRequest createDistrictRequest) {
        if (createDistrictRequest == null) {
            return null;
        }

        District district = new District();
        district.setName(createDistrictRequest.getName());
        district.setCity(cityService.getById(createDistrictRequest.getCityId()));

        return district;
    }

    public abstract List<GetDistrictResponse> districtListToGetDistrictResponseList(List<District> districts);
}

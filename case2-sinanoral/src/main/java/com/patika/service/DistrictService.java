package com.patika.service;

import com.patika.dao.DistrictDao;
import com.patika.enums.errors.ErrorMessage;
import com.patika.exception.NoSuchElementFoundException;
import com.patika.mapper.DistrictMapper;
import com.patika.model.entity.District;
import com.patika.model.request.CreateDistrictRequest;
import com.patika.model.response.GetDistrictResponse;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import com.patika.utilities.results.SuccessDataResult;
import com.patika.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictDao districtDao;
    private final DistrictMapper mapper;

    public Result create(CreateDistrictRequest createDistrictRequest) {
        District district = mapper.createDistrictRequestToDistrict(createDistrictRequest);
        districtDao.save(district);
        return new SuccessResult("District created");
    }

    public DataResult<List<GetDistrictResponse>> getDistrictsByCityId(Long id) {
        List<District> districts = getDistrictsByCityIdWithControl(id);
        List<GetDistrictResponse> districtResponses = mapper.districtListToGetDistrictResponseList(districts);
        return new SuccessDataResult<>(districtResponses);
    }

    private List<District> getDistrictsByCityIdWithControl(Long id) {
        List<District> districts = districtDao.getDistrictsByCityId(id);
        if(districts.isEmpty())
            throw new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND);
        return districts;
    }

    public District getById(Long id) {
        return districtDao.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND));
    }
}

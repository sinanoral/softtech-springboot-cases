package com.patika.service;

import com.patika.dao.NeighborhoodDao;
import com.patika.enums.errors.ErrorMessage;
import com.patika.exception.NoSuchElementFoundException;
import com.patika.mapper.NeighborhoodMapper;
import com.patika.model.entity.Neighborhood;
import com.patika.model.request.CreateNeighborhoodRequest;
import com.patika.model.request.UpdateNeighborhoodRequest;
import com.patika.model.response.GetNeighborhoodResponse;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import com.patika.utilities.results.SuccessDataResult;
import com.patika.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NeighborhoodService {

    private final NeighborhoodDao neighborhoodDao;
    private final NeighborhoodMapper mapper;

    public Result create(CreateNeighborhoodRequest createNeighborhoodRequest) {
        Neighborhood neighborhood = mapper.createNeighborhoodRequestToNeighborhood(createNeighborhoodRequest);
        neighborhoodDao.save(neighborhood);
        return new SuccessResult("Neighborhood created");
    }

    @Transactional
    public Result updateNameById(Long id, UpdateNeighborhoodRequest updateNeighborhoodRequest) {
        boolean isUpdated = neighborhoodDao.setNeighborhoodNameById(updateNeighborhoodRequest.getName(), id);
        if(!isUpdated)
            throw new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND);
        return new SuccessResult("Neighborhood's name updated");
    }

    public DataResult<List<GetNeighborhoodResponse>> getNeighborhoodsByDistrictId(Long id) {
        List<Neighborhood> neighborhoods = getNeighborhoodsByDistrictIdWithControl(id);
        List<GetNeighborhoodResponse> neighborhoodResponses = mapper.neighborhoodListToGetNeighborhoodResponseList(neighborhoods);
        return new SuccessDataResult<>(neighborhoodResponses);
    }

    private List<Neighborhood> getNeighborhoodsByDistrictIdWithControl(Long id) {
        List<Neighborhood> neighborhoods = neighborhoodDao.getNeighborhoodsByDistrict_Id(id);
        if(neighborhoods.isEmpty())
            throw new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND);
        return neighborhoods;
    }

    public Neighborhood getById(Long id) {
        return neighborhoodDao.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND));
    }
}

package com.patika.service;

import com.patika.dao.StreetDao;
import com.patika.enums.errors.ErrorMessage;
import com.patika.exception.NoSuchElementFoundException;
import com.patika.mapper.StreetMapper;
import com.patika.model.entity.Street;
import com.patika.model.request.CreateStreetRequest;
import com.patika.model.request.UpdateStreetRequest;
import com.patika.model.response.GetStreetResponse;
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
public class StreetService {
    private final StreetDao streetDao;
    private final StreetMapper mapper;

    public Result create(CreateStreetRequest createStreetRequest) {
        Street street = mapper.createStreetRequestToStreet(createStreetRequest);
        streetDao.save(street);
        return new SuccessResult("Street created");
    }

    @Transactional
    public Result updateNameById(Long id, UpdateStreetRequest updateStreetRequest) {
        boolean isUpdated = streetDao.setStreetNameById(updateStreetRequest.getName(), id);
        if(!isUpdated)
            throw new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND);
        return new SuccessResult("Street's name updated");
    }

    public DataResult<List<GetStreetResponse>> getStreetsByNeighborhoodId(Long id) {
        List<Street> streets = getStreetsByNeighborhoodIdWithControl(id);
        List<GetStreetResponse> streetResponses = mapper.streetListToGetStreetResponseList(streets);
        return new SuccessDataResult<>(streetResponses);
    }

    private List<Street> getStreetsByNeighborhoodIdWithControl(Long id) {
        List<Street> streets = streetDao.getStreetsByNeighborhood_Id(id);
        if(streets.isEmpty())
            throw new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND);
        return streets;
    }

    public Street getById(Long id) {
        return streetDao.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND));
    }
}

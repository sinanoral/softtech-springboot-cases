package com.patika.service;

import com.patika.dao.CityDao;
import com.patika.enums.errors.ErrorMessage;
import com.patika.exception.NoSuchElementFoundException;
import com.patika.mapper.CityMapper;
import com.patika.model.entity.City;
import com.patika.model.request.CreateCityRequest;
import com.patika.model.response.GetCityResponse;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import com.patika.utilities.results.SuccessDataResult;
import com.patika.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityDao cityDao;
    private final CityMapper mapper;

    public Result create(CreateCityRequest createCityRequest) {
        City city = mapper.createCityRequestToCity(createCityRequest);
        cityDao.save(city);
        return new SuccessResult("City created");
    }

    public DataResult<GetCityResponse> getByPlateNo(String plateNo) {
        City city = getCityByPlateWithControl(plateNo);
        GetCityResponse getCityResponse = mapper.countryToGetCountryResponse(city);
        return new SuccessDataResult<>(getCityResponse);
    }

    private City getCityByPlateWithControl(String plateNo) {
        City city = cityDao.getByPlateNo(plateNo);
        if(city == null) {
            throw new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND);
        }
        return city;
    }

    public City getById(Long id) {
        return cityDao.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND));
    }
}

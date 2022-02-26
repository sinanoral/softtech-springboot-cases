package com.patika.service;

import com.patika.dao.CountryDao;
import com.patika.enums.errors.ErrorMessage;
import com.patika.exception.NoSuchElementFoundException;
import com.patika.mapper.CountryMapper;
import com.patika.model.entity.Country;
import com.patika.model.request.CreateCountryRequest;
import com.patika.model.response.GetCountryResponse;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import com.patika.utilities.results.SuccessDataResult;
import com.patika.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryDao countryDao;
    private final CountryMapper mapper;

    public Result create(CreateCountryRequest createCountryRequest) {
        Country country = mapper.createCountryRequestToCountry(createCountryRequest);
        country.setName(country.getName().toLowerCase());
        countryDao.save(country);
        return new SuccessResult("Country created");
    }

    public DataResult<GetCountryResponse> getByCode(String code) {
        Country country = getCountryByCodeWithControl(code);
        GetCountryResponse getCountryResponse = mapper.countryToGetCountryResponse(country);
        return new SuccessDataResult<>(getCountryResponse);
    }

    private Country getCountryByCodeWithControl(String code) {
        Country country = countryDao.getByCode(code);
        if(country == null)
            throw new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND);
        return country;
    }

    public Country getById(Long id) {
        return countryDao.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND));
    }
}

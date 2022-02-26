package com.patika.service;

import com.patika.dao.AddressDao;
import com.patika.enums.errors.ErrorMessage;
import com.patika.exception.NoSuchElementFoundException;
import com.patika.mapper.AddressMapper;
import com.patika.model.entity.Address;
import com.patika.model.request.CreateAddressRequest;
import com.patika.model.response.GetAddressResponse;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import com.patika.utilities.results.SuccessDataResult;
import com.patika.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressDao addressDao;
    private final AddressMapper mapper;

    public Result create(CreateAddressRequest createAddressRequest) {
        Address address = mapper.createAddressRequestToAddress(createAddressRequest);
        addressDao.save(address);
        return new SuccessResult("Address created");
    }

    public DataResult<List<GetAddressResponse>> getAll() {
        List<Address> addresses = addressDao.findAll();
        List<GetAddressResponse> getAddressResponseList = mapper.addressListToGetAddressResponseList(addresses);
        return new SuccessDataResult<>(getAddressResponseList);
    }

    public DataResult<GetAddressResponse> getById(Long id) {
        Address address = addressDao.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND));

        GetAddressResponse getAddressResponse = mapper.addressToGetAddressResponse(address);
        return new SuccessDataResult<>(getAddressResponse);
    }

    public Result deleteById(Long id) {
        Address address = addressDao.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException(ErrorMessage.ITEM_NOT_FOUND));

        addressDao.delete(address);
        return new SuccessResult("Address deleted");
    }
}

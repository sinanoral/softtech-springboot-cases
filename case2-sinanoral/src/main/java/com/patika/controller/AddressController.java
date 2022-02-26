package com.patika.controller;

import com.patika.model.request.*;
import com.patika.model.response.*;
import com.patika.service.*;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AddressController {

    private final AddressService addressService;
    private final CountryService countryService;
    private final CityService cityService;
    private final DistrictService districtService;
    private final StreetService streetService;
    private final NeighborhoodService neighborhoodService;

    @Operation(summary = "gets all the addresses", tags = {"addresses"})
    @GetMapping("/addresses")
    public DataResult<List<GetAddressResponse>> getAll() {
        return addressService.getAll();
    }

    @Operation(summary = "gets an address by id", tags = {"addresses"})
    @GetMapping("/addresses/{id}")
    public DataResult<GetAddressResponse> getAddressById(@PathVariable Long id) {
        return addressService.getById(id);
    }

    @Operation(summary = "creates an address", tags = {"addresses"})
    @PostMapping("/addresses")
    public Result createAddress(@RequestBody CreateAddressRequest address) {
        return addressService.create(address);
    }

    @Operation(summary = "deletes an address by id", tags = {"addresses"})
    @DeleteMapping("/addresses/{id}")
    public Result deleteAddressById(@PathVariable Long id) {
        return addressService.deleteById(id);
    }

    @Operation(summary = "creates a country", tags = {"countries"})
    @PostMapping("/countries")
    public Result createCountry(@RequestBody CreateCountryRequest createCountryRequest) {
        return countryService.create(createCountryRequest);
    }

    @Operation(summary = "gets a country by code", tags = {"countries"})
    @GetMapping("/countries")
    public DataResult<GetCountryResponse> getCountryByCode(@RequestParam String code) {
        return countryService.getByCode(code.toLowerCase());
    }

    @Operation(summary = "creates a city", tags = {"cities"})
    @PostMapping("/cities")
    public Result createCity(@RequestBody CreateCityRequest createCityRequest) {
        return cityService.create(createCityRequest);
    }

    @Operation(summary = "gets a city by plate number", tags = {"cities"})
    @GetMapping("/cities")
    public DataResult<GetCityResponse> getCityByPlateNo(@RequestParam String plateNo) {
        return cityService.getByPlateNo(plateNo);
    }

    @Operation(summary = "gets districts of a city by city id", tags = {"cities"})
    @GetMapping("/cities/{id}/districts")
    public DataResult<List<GetDistrictResponse>> getDistrictsByCityId(@PathVariable Long id) {
        return districtService.getDistrictsByCityId(id);
    }

    @Operation(summary = "creates a district", tags = {"districts"})
    @PostMapping("/districts")
    public Result createDistrict(@RequestBody CreateDistrictRequest createDistrictRequest) {
        return districtService.create(createDistrictRequest);
    }

    @Operation(summary = "gets neighborhoods of a district by district id", tags = {"districts"})
    @GetMapping("/districts/{id}/neighborhoods")
    public DataResult<List<GetNeighborhoodResponse>> getNeighborhoodsByDistrictId(@PathVariable Long id) {
        return neighborhoodService.getNeighborhoodsByDistrictId(id);
    }

    @Operation(summary = "creates a neighborhood", tags = {"neighborhoods"})
    @PostMapping("/neighborhoods")
    public Result createDistrict(@RequestBody CreateNeighborhoodRequest createNeighborhoodRequest) {
        return neighborhoodService.create(createNeighborhoodRequest);
    }

    @Operation(summary = "updates name of a neighborhood by id", tags = {"neighborhoods"})
    @PutMapping("/neighborhoods/{id}")
    public Result updateNeighborhoodNameById(@PathVariable Long id, @RequestBody UpdateNeighborhoodRequest updateNeighborhoodRequest) {
        return neighborhoodService.updateNameById(id, updateNeighborhoodRequest);
    }

    @Operation(summary = "gets streets of a neighborhood by neighborhood id", tags = {"neighborhoods"})
    @GetMapping("/neighborhoods/{id}/streets")
    public DataResult<List<GetStreetResponse>> getStreetsByNeighborhoodId(@PathVariable Long id) {
        return streetService.getStreetsByNeighborhoodId(id);
    }

    @Operation(summary = "creates a street", tags = {"streets"})
    @PostMapping("/streets")
    public Result createStreet(@RequestBody CreateStreetRequest createStreetRequest) {
        return streetService.create(createStreetRequest);
    }

    @Operation(summary = "updates name of a street by id", tags = {"streets"})
    @PutMapping("/streets/{id}")
    public Result updateCityNameById(@PathVariable Long id, @RequestBody UpdateStreetRequest updateStreetRequest) {
        return streetService.updateNameById(id, updateStreetRequest);
    }
}

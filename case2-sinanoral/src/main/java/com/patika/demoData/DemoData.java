package com.patika.demoData;

import com.patika.dao.*;
import com.patika.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DemoData {

    private final CountryDao countryDao;
    private final CityDao cityDao;
    private final DistrictDao districtDao;
    private final NeighborhoodDao neighborhoodDao;
    private final StreetDao streetDao;
    private final AddressDao addressDao;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        System.out.println(event.getTimeTaken());

        Country country = new Country(1L, "Turkiye", "tr");
        City city = new City(1L, "Istanbul", "34", country);
        District district = new District(1L, "Maltepe", city);
        Neighborhood neighborhood = new Neighborhood(1L, "Findikli", district);
        Street street = new Street(1L, "Cankaya", neighborhood);
        Address address = new Address(1L, country, city, district, neighborhood, street, "4", "3");

        countryDao.save(country);
        cityDao.save(city);
        districtDao.save(district);
        neighborhoodDao.save(neighborhood);
        streetDao.save(street);
        addressDao.save(address);
    }
}

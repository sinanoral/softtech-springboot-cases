package com.patika.dao;

import com.patika.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City, Long> {
    City getByPlateNo(String plateNo);
}

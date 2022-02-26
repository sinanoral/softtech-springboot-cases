package com.patika.dao;

import com.patika.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictDao extends JpaRepository<District, Long> {
    List<District> getDistrictsByCityId(Long id);
}

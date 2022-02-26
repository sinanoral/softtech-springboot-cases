package com.patika.dao;

import com.patika.model.entity.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeighborhoodDao extends JpaRepository<Neighborhood, Long> {
    @Modifying
    @Query("update Neighborhood n set n.name = ?1 where n.id = ?2")
    boolean setNeighborhoodNameById(String name, Long id);

    List<Neighborhood> getNeighborhoodsByDistrict_Id(Long id);
}

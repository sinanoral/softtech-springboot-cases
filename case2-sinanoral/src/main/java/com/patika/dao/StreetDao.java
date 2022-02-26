package com.patika.dao;

import com.patika.model.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetDao extends JpaRepository<Street, Long> {
    @Modifying
    @Query("update City c set c.name = ?1 where c.id = ?2")
    boolean setStreetNameById(String name, Long id);

    List<Street> getStreetsByNeighborhood_Id(Long id);
}

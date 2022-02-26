package com.patika.dao;

import com.patika.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    @Modifying
    @Query("update Product p set p.price = ?2 where p.id = ?1")
    void setPriceById(Long id, BigDecimal price);
}

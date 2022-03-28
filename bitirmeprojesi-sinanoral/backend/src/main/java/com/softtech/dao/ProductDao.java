package com.softtech.dao;

import com.softtech.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> getAllByCategory_Id(Long id);

    List<Product> getAllByVatInclusivePriceBetween(BigDecimal min, BigDecimal max);
}

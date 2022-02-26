package com.patika.dao;

import com.patika.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//TODO(Sinan: Convert to generic repository)

@Repository
public interface AddressDao extends JpaRepository<Address, Long> {
    @Override
    void delete(Address entity);
}

package com.softtech.dao;

import com.softtech.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Boolean existsAllByUserName(String userName);
}

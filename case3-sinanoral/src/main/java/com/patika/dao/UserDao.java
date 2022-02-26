package com.patika.dao;

import com.patika.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Long deleteUserByUserNameAndPhoneNumber(String userName, String phoneNumber);
}

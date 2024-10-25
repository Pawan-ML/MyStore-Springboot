package com.ijse.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.database.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Custom Queries
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

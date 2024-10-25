package com.ijse.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.database.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}

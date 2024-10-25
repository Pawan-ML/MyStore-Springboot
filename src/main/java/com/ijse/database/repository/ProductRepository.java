package com.ijse.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.database.entity.Category;
import com.ijse.database.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //Custom Query to get products by category
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findProductsByCategory(@Param("category") Category category);
    
}

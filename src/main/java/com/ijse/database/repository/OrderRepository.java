package com.ijse.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.database.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    
}

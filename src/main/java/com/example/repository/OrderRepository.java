package com.example.repository;

import com.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByProductId(int product_id);
    Order findByProductIdAndType(int product_id, String type);
    Order findByProductIdAndClientIdAndType(int product_id, int client_id, String type);
    List<Order> findByClientId(int client_id);
}

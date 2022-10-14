package com.vodafone.orderapi.repository;

import com.vodafone.orderapi.data.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByEmailAndProductID(String email, String productID);
}

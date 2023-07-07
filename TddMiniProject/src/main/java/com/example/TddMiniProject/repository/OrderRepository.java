package com.example.TddMiniProject.repository;

import com.example.TddMiniProject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT MAX(o.id) FROM Order o")
    Long getMaxId();
}

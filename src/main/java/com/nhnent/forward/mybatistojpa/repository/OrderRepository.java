package com.nhnent.forward.mybatistojpa.repository;

import com.nhnent.forward.mybatistojpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

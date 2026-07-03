package com.foodg.order.repository;

import com.foodg.common.enums.OrderStatus;
import com.foodg.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByRestaurantId(Long restaurantId);

    List<Order> findByCustomerIdAndOrderStatus(
            Long customerId,
            OrderStatus orderStatus
    );

    List<Order> findByRestaurantIdAndOrderStatus(
            Long restaurantId,
            OrderStatus orderStatus
    );

    boolean existsByCustomerIdAndOrderStatusIn(
            Long customerId,
            List<OrderStatus> statuses
    );

    List<Order> findByCustomerIdOrderByOrderedAtDesc(
            Long customerId
    );

    List<Order> findByRestaurantIdOrderByOrderedAtDesc(
            Long restaurantId
    );

    @Query("""
           SELECT COALESCE(SUM(o.totalAmount),0)
           FROM Order o
           """)
    BigDecimal calculateTotalRevenue();
}
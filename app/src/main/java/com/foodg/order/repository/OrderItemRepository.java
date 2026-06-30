package com.foodg.order.repository;

import com.foodg.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long orderId);

    void deleteByOrderId(Long orderId);

    boolean existsByOrderIdAndMenuItemId(
            Long orderId,
            Long menuItemId
    );

}
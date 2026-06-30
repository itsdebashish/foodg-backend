package com.foodg.order.dto;

import com.foodg.common.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(

        Long id,

        Long customerId,

        String customerName,

        Long restaurantId,

        String restaurantName,

        List<OrderItemResponse> items,

        OrderStatus orderStatus,

        BigDecimal totalAmount,

        LocalDateTime orderedAt,

        LocalDateTime deliveredAt

) {
}
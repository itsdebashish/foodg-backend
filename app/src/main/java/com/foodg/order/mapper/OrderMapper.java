package com.foodg.order.mapper;
import com.foodg.common.enums.OrderStatus;
import com.foodg.menu.entity.MenuItem;
import com.foodg.order.dto.CreateOrderRequest;
import com.foodg.order.dto.OrderItemRequest;
import com.foodg.order.dto.OrderItemResponse;
import com.foodg.order.dto.OrderResponse;
import com.foodg.order.entity.Order;
import com.foodg.order.entity.OrderItem;
import com.foodg.restaurant.entity.Restaurant;
import com.foodg.user.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class OrderMapper {
    public Order toEntity(CreateOrderRequest request,
                          User customer,
                          Restaurant restaurant) {

        if (request == null) {
            return null;
        }

        Order order = new Order();

        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setOrderedAt(LocalDateTime.now());
        order.setTotalAmount(BigDecimal.ZERO);

        return order;
    }

    public OrderItem toOrderItem(Order order,
                                 MenuItem menuItem,
                                 Integer quantity) {

        OrderItem item = new OrderItem();

        item.setOrder(order);
        item.setMenuItem(menuItem);
        item.setQuantity(quantity);
        item.setPrice(menuItem.getPrice());

        return item;
    }

    public OrderResponse toResponse(Order order) {

        if (order == null) {
            return null;
        }

        List<OrderItemResponse> items =
                order.getOrderItems() == null
                        ? Collections.emptyList()
                        : order.getOrderItems()
                        .stream()
                        .map(this::toOrderItemResponse)
                        .toList();

        return new OrderResponse(

                order.getId(),

                order.getCustomer().getId(),
                order.getCustomer().getName(),

                order.getRestaurant().getId(),
                order.getRestaurant().getName(),

                items,

                order.getOrderStatus(),

                order.getTotalAmount(),

                order.getOrderedAt(),

                order.getDeliveredAt()
        );
    }

    public OrderItemResponse toOrderItemResponse(OrderItem item) {

        BigDecimal subtotal =
                item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity()));

        return new OrderItemResponse(

                item.getId(),

                item.getMenuItem().getId(),

                item.getMenuItem().getName(),

                item.getQuantity(),

                item.getPrice(),

                subtotal
        );
    }

    public OrderItem fromOrderItem(OrderItemRequest request, MenuItem menuItem) {

        return OrderItem.builder()
                .menuItem(menuItem)
                .quantity(request.quantity())
                .price(menuItem.getPrice())
                .build();
    }}
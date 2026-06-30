package com.foodg.order.service;

import com.foodg.order.dto.CreateOrderRequest;
import com.foodg.order.dto.OrderItemResponse;
import com.foodg.order.dto.OrderResponse;
import com.foodg.order.dto.UpdateOrderStatusRequest;
import com.foodg.order.entity.OrderItem;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(CreateOrderRequest request);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getOrdersByCustomer(Long customerId);

    List<OrderResponse> getOrdersByRestaurant(Long restaurantId);

    OrderResponse getOrderStatus(Long id);

    OrderResponse cancelOrder(Long id, UpdateOrderStatusRequest request);

    OrderResponse acceptOrder(Long id, UpdateOrderStatusRequest request);

    OrderResponse prepareOrder(Long id, UpdateOrderStatusRequest request);

    OrderResponse readyOrder(Long id, UpdateOrderStatusRequest request);

    OrderResponse outForDelivery(Long id, UpdateOrderStatusRequest request);

    OrderResponse completeOrder(Long id, UpdateOrderStatusRequest request);
}
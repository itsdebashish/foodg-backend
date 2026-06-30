package com.foodg.order.service;
import com.foodg.common.enums.OrderStatus;
import com.foodg.common.exception.ResourceNotFoundException;
import com.foodg.menu.entity.MenuItem;
import com.foodg.menu.repository.MenuRepository;
import com.foodg.order.dto.*;
import com.foodg.order.entity.Order;
import com.foodg.order.entity.OrderItem;
import com.foodg.order.mapper.OrderMapper;
import com.foodg.order.repository.OrderRepository;
import com.foodg.restaurant.entity.Restaurant;
import com.foodg.restaurant.repository.RestaurantRepository;
import com.foodg.user.entity.User;
import com.foodg.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public OrderResponse placeOrder(CreateOrderRequest request) {

        // 1. Load customer
        User customer = userRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        // 2. Load restaurant
        Restaurant restaurant = restaurantRepository.findById(request.restaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not Found"));

        // 3. Create Order
        Order order = orderMapper.toEntity(request, customer, restaurant);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        // 4 & 5. Load MenuItems and Create OrderItems
        for (OrderItemRequest itemRequest : request.items()) {

            MenuItem menuItem = menuRepository.findById(itemRequest.menuItemId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Menu Item not found : " + itemRequest.menuItemId()));

            OrderItem orderItem = orderMapper.fromOrderItem(itemRequest, menuItem);

            orderItem.setOrder(order);

            orderItems.add(orderItem);

            // price * quantity
            BigDecimal itemTotal = menuItem.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.quantity()));

            total = total.add(itemTotal);
        }

        // 6. Set order details
        order.setOrderItems(orderItems);
        order.setTotalAmount(total);

        // 7. Save order
        Order savedOrder = orderRepository.save(order);

        // 8. Return response
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return orderMapper.toResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByCustomer(Long customerId) {

        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByRestaurant(Long restaurantId) {

        return orderRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderStatus(Long id) {

        return getOrderById(id);
    }

    @Override
    public OrderResponse cancelOrder(Long id, UpdateOrderStatusRequest request) {

        Order order = getOrder(id);

        order.setOrderStatus(OrderStatus.CANCELLED);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse acceptOrder(Long id, UpdateOrderStatusRequest request) {

        Order order = getOrder(id);

        order.setOrderStatus(OrderStatus.ACCEPTED);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse prepareOrder(Long id, UpdateOrderStatusRequest request) {

        Order order = getOrder(id);

        order.setOrderStatus(OrderStatus.PREPARING);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse readyOrder(Long id, UpdateOrderStatusRequest request) {

        Order order = getOrder(id);

        order.setOrderStatus(OrderStatus.READY_FOR_PICKUP);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse outForDelivery(Long id, UpdateOrderStatusRequest request) {

        Order order = getOrder(id);

        order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse completeOrder(Long id, UpdateOrderStatusRequest request) {

        Order order = getOrder(id);

        order.setOrderStatus(OrderStatus.DELIVERED);
        order.setDeliveredAt(LocalDateTime.now());

        return orderMapper.toResponse(orderRepository.save(order));
    }

    private Order getOrder(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}
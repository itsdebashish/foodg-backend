package com.foodg.order.controller;


import com.foodg.order.dto.CreateOrderRequest;
import com.foodg.order.dto.OrderItemResponse;
import com.foodg.order.dto.OrderResponse;
import com.foodg.order.dto.UpdateOrderStatusRequest;
import com.foodg.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(
            @Valid @RequestBody CreateOrderRequest request) {

        OrderResponse response = orderService.placeOrder(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.getOrderById(id)
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getCustomerOrderHistory(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                orderService.getOrdersByCustomer(customerId)
        );
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderResponse>> getRestaurantIncomingOrders(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                orderService.getOrdersByRestaurant(restaurantId)
        );
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<OrderResponse> getStatus(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.getOrderStatus(id)
        );
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {

        return ResponseEntity.ok(
                orderService.cancelOrder(id, request)
        );
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<OrderResponse> markRestaurantAcceptOrder(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {

        return ResponseEntity.ok(
                orderService.acceptOrder(id, request)
        );
    }

    @PutMapping("/{id}/prepare")
    public ResponseEntity<OrderResponse> markPrepareOrder(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {

        return ResponseEntity.ok(
                orderService.prepareOrder(id, request)
        );
    }

    @PutMapping("/{id}/ready")
    public ResponseEntity<OrderResponse> markReadyOrder(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {

        return ResponseEntity.ok(
                orderService.readyOrder(id, request)
        );
    }

    @PutMapping("/{id}/deliver")
    public ResponseEntity<OrderResponse> markOutForDelivery(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {

        return ResponseEntity.ok(
                orderService.outForDelivery(id, request)
        );
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<OrderResponse> markDelivered(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {

        return ResponseEntity.ok(
                orderService.completeOrder(id, request)
        );
    }
}
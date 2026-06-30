package com.foodg.analytics.controller;

import com.foodg.analytics.dto.DashboardResponse;
import com.foodg.analytics.dto.OrderCountResponse;
import com.foodg.analytics.dto.OrderTrendResponse;
import com.foodg.analytics.dto.RevenueResponse;
import com.foodg.analytics.service.AnalyticsService;
import com.foodg.restaurant.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getDashboard() {
        return ResponseEntity.ok(analyticsService.getDashboard());
    }

    @GetMapping("/revenue")
    public ResponseEntity<RevenueResponse> getRevenue() {
        return ResponseEntity.ok(analyticsService.getRevenue());
    }

    @GetMapping("/orders/count")
    public ResponseEntity<OrderCountResponse> getTotalOrder() {
        return ResponseEntity.ok(analyticsService.getTotalOrders());
    }

    @GetMapping("/orders/trends")
    public ResponseEntity<OrderTrendResponse> getOrderTrend() {
        return ResponseEntity.ok(analyticsService.getOrderTrend());
    }

    @GetMapping("/delivery-performance")
    public ResponseEntity<String> getDeliveryPerformance() {
        return ResponseEntity.ok(analyticsService.getDeliveryPerformance());
    }
}
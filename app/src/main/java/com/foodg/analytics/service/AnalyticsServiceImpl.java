package com.foodg.analytics.service;

import com.foodg.analytics.dto.DashboardResponse;
import com.foodg.analytics.dto.OrderCountResponse;
import com.foodg.analytics.dto.OrderTrendResponse;
import com.foodg.analytics.dto.RevenueResponse;
import com.foodg.analytics.service.AnalyticsService;
import com.foodg.order.repository.OrderRepository;
import com.foodg.restaurant.dto.RestaurantResponse;
import com.foodg.restaurant.entity.Restaurant;
import com.foodg.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public DashboardResponse getDashboard() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalOrders(orderRepository.count());
        response.setTotalRestaurants(restaurantRepository.count());

        // Add other dashboard fields

        return response;
    }

    @Override
    public RevenueResponse getRevenue() {

        BigDecimal revenue = orderRepository.calculateTotalRevenue();

        RevenueResponse response = new RevenueResponse();
        response.setTotalRevenue(revenue);

        return response;
    }

    @Override
    public OrderCountResponse getTotalOrders() {

        OrderCountResponse response = new OrderCountResponse();
        response.setTotalOrders(orderRepository.count());

        return response;
    }

    @Override
    public OrderTrendResponse getOrderTrend() {

        return new OrderTrendResponse();
    }


    @Override
    public String getDeliveryPerformance() {
        return "Average delivery time : 28 minutes";
    }
}
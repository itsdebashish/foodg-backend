package com.foodg.analytics.service;

import com.foodg.analytics.dto.DashboardResponse;
import com.foodg.analytics.dto.OrderCountResponse;
import com.foodg.analytics.dto.OrderTrendResponse;
import com.foodg.analytics.dto.RevenueResponse;
import com.foodg.restaurant.dto.RestaurantResponse;

public interface AnalyticsService {

    DashboardResponse getDashboard();

    RevenueResponse getRevenue();

    OrderCountResponse getTotalOrders();

    OrderTrendResponse getOrderTrend();

    String getDeliveryPerformance();
}
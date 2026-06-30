package com.foodg.analytics.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private Long totalOrders;

    private Long totalCustomers;

    private Long totalRestaurants;

    private BigDecimal totalRevenue;

    private Long completedOrders;

    private Long cancelledOrders;

    private Long pendingOrders;
}
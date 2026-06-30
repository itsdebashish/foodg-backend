package com.foodg.analytics.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueResponse {

    private BigDecimal totalRevenue;

    private BigDecimal todayRevenue;

    private BigDecimal weeklyRevenue;

    private BigDecimal monthlyRevenue;
}
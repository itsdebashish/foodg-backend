package com.foodg.analytics.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCountResponse {

    private Long totalOrders;

    private Long pendingOrders;

    private Long preparingOrders;

    private Long deliveredOrders;

    private Long cancelledOrders;
}
package com.foodg.analytics.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrendData {

    private String label;

    private Long orderCount;
}
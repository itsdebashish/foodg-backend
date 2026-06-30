package com.foodg.analytics.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderTrendResponse {

    private List<TrendData> trends;
}
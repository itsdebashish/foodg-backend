package com.foodg.order.dto;


import com.foodg.common.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(

        @NotNull
        OrderStatus orderStatus

) {
}
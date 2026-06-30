package com.foodg.payment.dto;

import java.math.BigDecimal;

public record RefundRequest(
        Long paymentId,
        BigDecimal refundAmount,
        String reason
) {
}
package com.foodg.payment.dto;


import com.foodg.common.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private Long orderId;

    private BigDecimal amount;

    private String transactionId;

    private PaymentStatus paymentStatus;
}
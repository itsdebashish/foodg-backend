package com.foodg.payment.mapper;


import com.foodg.order.entity.Order;
import com.foodg.payment.dto.PaymentRequest;
import com.foodg.payment.dto.PaymentResponse;
import com.foodg.payment.entity.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentRequest request) {

        if (request == null) {
            return null;
        }

        return Payment.builder().build();
    }

    public void updateEntity(Payment payment, PaymentRequest request) {

        if (payment == null || request == null) {
            return;
        }

        payment.setAmount(request.getAmount());
        payment.setTransactionId(request.getTransactionId());
        payment.setPaymentStatus(request.getPaymentStatus());

        if (payment.getPaymentTime() == null) {
            payment.setPaymentTime(LocalDateTime.now());
        }
    }


    public PaymentResponse toResponse(Payment payment) {

        if (payment == null) {
            return null;
        }

        Long orderId = null;

        if (payment.getOrder() != null) {
            orderId = payment.getOrder().getId();
        }

        return PaymentResponse.builder().build();
    }


    public void setOrder(Payment payment, Order order) {

        if (payment == null) {
            return;
        }

        payment.setOrder(order);
    }
}
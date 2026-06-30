package com.foodg.payment.service;


import com.foodg.payment.dto.PaymentRequest;
import com.foodg.payment.dto.PaymentResponse;
import com.foodg.payment.dto.RefundRequest;

public interface PaymentService {

    PaymentResponse processPayment(
            PaymentRequest request
    );

    PaymentResponse getPaymentById(
            Long id
    );

    PaymentResponse getPaymentByOrderId(
            Long orderId
    );

    PaymentResponse refundPayment(
            Long orderId,
            RefundRequest request
    );

    PaymentResponse checkPaymentStatus(
            Long paymentId
    );
}
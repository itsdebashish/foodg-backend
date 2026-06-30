package com.foodg.payment.service;

import com.foodg.common.enums.PaymentStatus;
import com.foodg.common.exception.ResourceNotFoundException;
import com.foodg.order.entity.Order;
import com.foodg.order.repository.OrderRepository;
import com.foodg.payment.dto.PaymentRequest;
import com.foodg.payment.dto.PaymentResponse;
import com.foodg.payment.dto.RefundRequest;
import com.foodg.payment.entity.Payment;
import com.foodg.payment.mapper.PaymentMapper;
import com.foodg.payment.repository.PaymentRepository;
import com.foodg.payment.service.PaymentService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {

        if (request.getOrderId() == null) {
            throw new IllegalArgumentException("Order id is required");
        }

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found: " + request.getOrderId()
                        )
                );

        if (paymentRepository.existsByOrderId(order.getId())) {
            throw new IllegalStateException(
                    "Payment already exists for order"
            );
        }

        Payment payment = paymentMapper.toEntity(request);

        paymentMapper.setOrder(payment, order);

        // process payment and get transaction id

        payment.setTransactionId(
                UUID.randomUUID().toString()
        );

        payment.setPaymentStatus(
                PaymentStatus.SUCCESS
        );

        Payment savedPayment =
                paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found: " + id
                        )
                );

        return paymentMapper.toResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentByOrderId(Long orderId) {

        Payment payment =
                paymentRepository.findByOrderId(orderId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment not found for order: "
                                                + orderId
                                )
                        );

        return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse refundPayment(
            Long orderId,
            RefundRequest request
    ) {

        Payment payment =
                paymentRepository.findByOrderId(orderId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment not found for order"
                                )
                        );

        if (payment.getPaymentStatus()
                != PaymentStatus.SUCCESS) {

            throw new IllegalStateException(
                    "Only successful payments can be refunded"
            );
        }


        /*
          Here call payment gateway refund API
          stripe.refund(transactionId)
        */

        payment.setPaymentStatus(
                PaymentStatus.REFUNDED
        );

        Payment refundedPayment =
                paymentRepository.save(payment);

        return paymentMapper.toResponse(refundedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse checkPaymentStatus(Long paymentId) {

        Payment payment =
                paymentRepository.findById(paymentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment not found: "
                                                + paymentId
                                )
                        );

        return paymentMapper.toResponse(payment);
    }
}
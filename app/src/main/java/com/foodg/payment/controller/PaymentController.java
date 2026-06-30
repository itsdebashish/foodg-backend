package com.foodg.payment.controller;


import com.foodg.payment.dto.PaymentRequest;
import com.foodg.payment.dto.PaymentResponse;
import com.foodg.payment.dto.RefundRequest;
import com.foodg.payment.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> processPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response =
                paymentService.processPayment(request);

        return ResponseEntity
                .status(201)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentDetails(
            @PathVariable Long id) {

        PaymentResponse response =
                paymentService.getPaymentById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> paymentByOrder(
            @PathVariable Long orderId) {

        PaymentResponse response =
                paymentService.getPaymentByOrderId(orderId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refund/{orderId}")
    public ResponseEntity<PaymentResponse> refundPayment(
            @PathVariable Long orderId,
            @Valid @RequestBody RefundRequest request) {

        PaymentResponse response =
                paymentService.refundPayment(
                        orderId,
                        request
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{paymentId}")
    public ResponseEntity<PaymentResponse> checkPaymentStatus(
            @PathVariable Long paymentId) {

        PaymentResponse response =
                paymentService.checkPaymentStatus(
                        paymentId
                );

        return ResponseEntity.ok(response);
    }
}
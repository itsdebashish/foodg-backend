package com.foodg.payment.repository;
import com.foodg.common.enums.PaymentStatus;
import com.foodg.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionId(String transactionId);

    Optional<Payment> findByOrderId(Long orderId);

    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

    boolean existsByTransactionId(String transactionId);

    boolean existsByOrderId(Long orderId);
}
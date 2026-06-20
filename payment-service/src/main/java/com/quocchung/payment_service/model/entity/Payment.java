package com.quocchung.payment_service.model.entity;

import com.quocchung.payment_service.utils.enums.PaymentMethod;
import com.quocchung.payment_service.utils.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false)
  private PaymentMethod paymentMethod;     // MOMO, VNPAY, VIETQR

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;            // PENDING, SUCCEEDED, FAILED

  private String failureReason;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
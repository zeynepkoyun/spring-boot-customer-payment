package com.example.customerpayment.payment.model.response;

import com.example.customerpayment.payment.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AllPaymentResponse {
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String productName;
  private Double productPrice;
  private OrderStatus paymentStatus;
}

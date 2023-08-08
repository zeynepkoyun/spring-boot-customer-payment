package com.example.customerpayment.payment.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CustomerPaymentResponse {
  private String productName;
  private Double productPrice;
  private LocalDateTime startDate;
}

package com.example.customerpayment.payment.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentResponse {
  private Double totalPayment;
}

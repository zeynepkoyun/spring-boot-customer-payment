package com.example.customerpayment.payment.model.request;

import com.example.customerpayment.payment.domain.PaymentType;
import lombok.Getter;

@Getter
public class PaymentRequest {
  private PaymentType paymentType;
}

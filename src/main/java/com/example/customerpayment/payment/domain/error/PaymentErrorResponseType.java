package com.example.customerpayment.payment.domain.error;

import com.example.customerpayment.common.error.ErrorMessage;

public enum PaymentErrorResponseType implements ErrorMessage {
  PAYMENT_TYPE_NOT_FOUND("Payment type not found"),
  MINIMUM_PAYMENT_AMOUNT("The minimum payment amount must be greater than 0 TL.");

  private String message;

  PaymentErrorResponseType(String s) {
    this.message = s;
  }

  @Override
  public String message() {
    return message;
  }
}

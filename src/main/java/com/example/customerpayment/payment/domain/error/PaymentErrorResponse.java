package com.example.customerpayment.payment.domain.error;

public class PaymentErrorResponse extends  RuntimeException {
  public PaymentErrorResponse(PaymentErrorResponseType customerErrorResponseType) {
    super(customerErrorResponseType.message());
  }

}

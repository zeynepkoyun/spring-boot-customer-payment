package com.example.customerpayment.customer.domain.error;

public class CustomerErrorResponse extends  RuntimeException {
  public CustomerErrorResponse(CustomerErrorResponseType customerErrorResponseType) {
    super(customerErrorResponseType.message());
  }

}

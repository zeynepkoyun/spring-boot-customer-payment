package com.example.customerpayment.customer.domain.error;

import com.example.customerpayment.common.error.ErrorMessage;

public enum CustomerErrorResponseType implements ErrorMessage {
  CUSTOMER_ALREADY_EXISTS("There is a record of the entered customer information. Enter your Card Number, Email and Customer Number correctly."),
  CUSTOMER_NOT_FOUND("Customer Not Found");

  private String message;

  CustomerErrorResponseType(String s) {
    this.message = s;
  }

  @Override
  public String message() {
    return message;
  }
}

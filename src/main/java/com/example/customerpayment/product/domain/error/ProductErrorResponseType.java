package com.example.customerpayment.product.domain.error;

import com.example.customerpayment.common.error.ErrorMessage;

public enum ProductErrorResponseType implements ErrorMessage {
  PRODUCT_NAME_ALREADY_EXISTS("The product name has been used before."),

  PRODUCT_NOT_FOUND("Product not found");

  private String message;

  ProductErrorResponseType(String s) {
    this.message = s;
  }

  @Override
  public String message() {
    return message;
  }
}

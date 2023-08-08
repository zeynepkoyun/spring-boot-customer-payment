package com.example.customerpayment.product.domain.error;

public class ProductErrorResponse extends  RuntimeException {
  public ProductErrorResponse(ProductErrorResponseType productErrorResponseType) {
    super(productErrorResponseType.message());
  }

}

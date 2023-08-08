package com.example.customerpayment.product.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductAddResponse {
  private String name;

  public String message(){
    return String.format("A new product called congrats %s has been added.",name);
  }
}

package com.example.customerpayment.product.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAddRequest {
  private String name;
  private Double price;
  private Integer stockNumber;
}

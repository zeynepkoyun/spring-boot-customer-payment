package com.example.customerpayment.customer.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerProductRequest {
  private List<Long> products;

}

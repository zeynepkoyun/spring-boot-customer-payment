package com.example.customerpayment.customer.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddRequest {
  private String name;
  private String  surname;
  @NotNull
  private String customerNumber;
  @NotNull
  private String cartNumber;
  @Email
  private String email;
}

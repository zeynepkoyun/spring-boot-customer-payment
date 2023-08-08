package com.example.customerpayment.customer.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerCreatedResponse {
  private String name;

  public String message(){
    return String.format("Tebrikler %s kaydınız başarıyla tamamlandı.",name);
  }
}

package com.example.customerpayment.customer.controller;

import com.example.customerpayment.customer.model.request.CustomerAddRequest;
import com.example.customerpayment.customer.model.request.CustomerProductRequest;
import com.example.customerpayment.customer.model.response.CustomerReceiveProductResponse;
import com.example.customerpayment.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  @PostMapping
  public ResponseEntity<String> createCustomer(@RequestBody CustomerAddRequest customerAddRequest) {
    return customerService.createCustomer(customerAddRequest);
  }

  @PutMapping("/order/{id}")
  public ResponseEntity<List<CustomerReceiveProductResponse>> receiveCustomerProduct(@RequestBody CustomerProductRequest customerProductRequest, @PathVariable Long id) {
    return customerService.receiveCustomerProducts(customerProductRequest, id);
  }

}

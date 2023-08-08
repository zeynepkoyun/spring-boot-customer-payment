package com.example.customerpayment.customer.service;

import com.example.customerpayment.customer.domain.CustomerEntity;
import com.example.customerpayment.customer.model.request.CustomerAddRequest;
import com.example.customerpayment.customer.model.request.CustomerProductRequest;
import com.example.customerpayment.customer.model.response.CustomerReceiveProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
  ResponseEntity<String> createCustomer(CustomerAddRequest customerAddRequest);

  ResponseEntity<List<CustomerReceiveProductResponse>> receiveCustomerProducts(CustomerProductRequest customerProductRequest, Long customerId);

}

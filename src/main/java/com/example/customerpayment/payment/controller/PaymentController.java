package com.example.customerpayment.payment.controller;

import com.example.customerpayment.payment.model.request.PaymentRequest;
import com.example.customerpayment.payment.model.response.AllPaymentResponse;
import com.example.customerpayment.payment.model.response.CustomerPaymentResponse;
import com.example.customerpayment.payment.model.response.PaymentResponse;
import com.example.customerpayment.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentService paymentService;

  @PutMapping("/{customerId}")
  public ResponseEntity<PaymentResponse> paymentForCustomerProducts(@PathVariable Long customerId, @RequestBody PaymentRequest paymentRequest) {
    return paymentService.paymentForCustomerProducts(customerId,paymentRequest);
  }
  @GetMapping("/{customerId}")
  public ResponseEntity<List<CustomerPaymentResponse>> customersPaymentList(@PathVariable Long customerId){
    return paymentService.customerPaymentList(customerId);
  }
  @GetMapping
  public ResponseEntity<List<AllPaymentResponse>> totalPaymentList(){
    return paymentService.totalPaymentList();
  }
}

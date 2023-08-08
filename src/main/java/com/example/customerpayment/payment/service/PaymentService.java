package com.example.customerpayment.payment.service;

import com.example.customerpayment.payment.model.request.PaymentRequest;
import com.example.customerpayment.payment.model.response.AllPaymentResponse;
import com.example.customerpayment.payment.model.response.CustomerPaymentResponse;
import com.example.customerpayment.payment.model.response.PaymentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {
  ResponseEntity<PaymentResponse> paymentForCustomerProducts(Long customerId, PaymentRequest paymentRequest);

  ResponseEntity<List<CustomerPaymentResponse>> customerPaymentList(Long customerId);
  ResponseEntity<List<AllPaymentResponse>> totalPaymentList();
}

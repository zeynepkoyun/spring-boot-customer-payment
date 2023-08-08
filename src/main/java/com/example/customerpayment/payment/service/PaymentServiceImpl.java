package com.example.customerpayment.payment.service;

import com.example.customerpayment.customer.domain.CustomerEntity;
import com.example.customerpayment.customer.domain.error.CustomerErrorResponse;
import com.example.customerpayment.customer.domain.error.CustomerErrorResponseType;
import com.example.customerpayment.customer.repository.CustomerRepository;
import com.example.customerpayment.payment.domain.OrderStatus;
import com.example.customerpayment.payment.domain.PaymentEntity;
import com.example.customerpayment.payment.domain.PaymentType;
import com.example.customerpayment.payment.domain.error.PaymentErrorResponse;
import com.example.customerpayment.payment.domain.error.PaymentErrorResponseType;
import com.example.customerpayment.payment.model.request.PaymentRequest;
import com.example.customerpayment.payment.model.response.AllPaymentResponse;
import com.example.customerpayment.payment.model.response.CustomerPaymentResponse;
import com.example.customerpayment.payment.model.response.PaymentResponse;
import com.example.customerpayment.payment.repository.PaymentRepository;
import com.example.customerpayment.product.domain.ProductEntity;
import com.example.customerpayment.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{
  private final CustomerRepository customerRepository;
  private final PaymentRepository paymentRepository;
  private final ProductRepository productRepository;

  @Override
  public ResponseEntity<PaymentResponse> paymentForCustomerProducts(Long customerId, PaymentRequest paymentRequest) {
    CustomerEntity customerInfo = customerRepository.findById(customerId).orElseThrow(() -> new CustomerErrorResponse(CustomerErrorResponseType.CUSTOMER_NOT_FOUND));
    Optional<PaymentType> paymentTypeOptional = findPaymentType(paymentRequest);
    if (paymentTypeOptional.isEmpty()){
      throw new PaymentErrorResponse(PaymentErrorResponseType.PAYMENT_TYPE_NOT_FOUND);
    }
    PaymentType paymentType = paymentTypeOptional.get();
    Double totalAmount = paymentRepository.findCustomersPaymentAmount(customerInfo);
    if (totalAmount <= 0.0){
      throw new PaymentErrorResponse(PaymentErrorResponseType.MINIMUM_PAYMENT_AMOUNT);
    }
    //odeme islemleri ile ilgili servise yonlendirilmeli ve sonra end_date/stockNumber güncellenmeli.
    paymentRepository.findPaymentEntitiesByCustomer(customerInfo).stream().forEach(paymentEntity -> {
      ProductEntity productEntity = productRepository.findById(paymentEntity.getProduct().getId()).orElseThrow();

      productEntity.setStockNumber(productEntity.getStockNumber()-1);
      productRepository.save(productEntity);

      paymentEntity.setEndDate(LocalDateTime.now());
      paymentEntity.setOrderStatus(OrderStatus.COMPLETED);
      paymentRepository.save(paymentEntity);
    });

//    paymentRepository.updateEndDateByCustomerNumber(LocalDateTime.now(),customerInfo);
    return ResponseEntity.status(HttpStatus.OK).body(PaymentResponse.builder().totalPayment(totalAmount).build());
  }

  @Override
  public ResponseEntity<List<CustomerPaymentResponse>> customerPaymentList(Long customerId) {
    CustomerEntity customerInfo = customerRepository.findById(customerId).orElseThrow(() -> new CustomerErrorResponse(CustomerErrorResponseType.CUSTOMER_NOT_FOUND));
    List<PaymentEntity> product = paymentRepository.findCustomerPaymentList(customerInfo);
    List<CustomerPaymentResponse> customerPaymentResponseList = new ArrayList<>();
    product.stream().forEach(paymentEntity -> {
      CustomerPaymentResponse customerPaymentResponse = CustomerPaymentResponse.builder()
          .productName(paymentEntity.getProduct().getName())
          .productPrice(paymentEntity.getProduct().getPrice())
          .startDate(paymentEntity.getStartDate())
          .build();
      customerPaymentResponseList.add(customerPaymentResponse);
    });
    return ResponseEntity.ok(customerPaymentResponseList);
  }

  @Override
  public ResponseEntity<List<AllPaymentResponse>> totalPaymentList() {
    List<Object[]> objectResults = paymentRepository.findAllPaymentList();
    List<AllPaymentResponse> allPaymentResponseList = new ArrayList<>();
    for (Object[] row : objectResults) {
      String productName = (String) row[0];
      Double productPrice = (Double) row[1];
      LocalDateTime startDate = (LocalDateTime) row[2];
      LocalDateTime endDate = (LocalDateTime) row[3];
      OrderStatus status = (OrderStatus) row[4];

      AllPaymentResponse allPaymentResponse = AllPaymentResponse.builder()
          .productName(productName)
          .productPrice(productPrice)
          .startDate(startDate)
          .endDate(endDate)
          .paymentStatus(status)
          .build();
      allPaymentResponseList.add(allPaymentResponse);
    }
    return ResponseEntity.ok(allPaymentResponseList);
  }

  public Optional<PaymentType> findPaymentType(PaymentRequest paymentRequest){
    return Arrays.stream(PaymentType.values()).filter(paymentType -> paymentType.name().equals(paymentRequest.getPaymentType().name()))
        .findFirst();
  }
}

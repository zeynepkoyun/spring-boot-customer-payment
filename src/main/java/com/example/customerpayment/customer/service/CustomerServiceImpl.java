package com.example.customerpayment.customer.service;

import com.example.customerpayment.customer.domain.CustomerEntity;
import com.example.customerpayment.customer.domain.error.CustomerErrorResponse;
import com.example.customerpayment.customer.domain.error.CustomerErrorResponseType;
import com.example.customerpayment.customer.model.request.CustomerAddRequest;
import com.example.customerpayment.customer.model.request.CustomerProductRequest;
import com.example.customerpayment.customer.model.response.CustomerCreatedResponse;
import com.example.customerpayment.customer.model.response.CustomerReceiveProductResponse;
import com.example.customerpayment.customer.repository.CustomerRepository;
import com.example.customerpayment.payment.domain.OrderStatus;
import com.example.customerpayment.payment.domain.PaymentEntity;
import com.example.customerpayment.payment.repository.PaymentRepository;
import com.example.customerpayment.product.domain.ProductEntity;
import com.example.customerpayment.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final PaymentRepository paymentRepository;
  private final ProductRepository productRepository;

  @Override
  public ResponseEntity<String> createCustomer(CustomerAddRequest customerAddRequest) {

    Integer customerCount = customerRepository.findCustomerByCartNumberAndEmailAndCustomerNumber(
        customerAddRequest.getCartNumber(),
        customerAddRequest.getEmail(),
        customerAddRequest.getCustomerNumber());
    if (customerCount != 0) {
      throw new CustomerErrorResponse(CustomerErrorResponseType.CUSTOMER_ALREADY_EXISTS);
    } else {
      CustomerEntity customerEntity = CustomerEntity.builder()
          .name(customerAddRequest.getName())
          .surname(customerAddRequest.getSurname())
          .customerNumber(customerAddRequest.getCustomerNumber())
          .cartNumber(customerAddRequest.getCartNumber())
          .email(customerAddRequest.getEmail())
          .build();
      CustomerEntity savedCustomer = customerRepository.save(customerEntity);
      return ResponseEntity.status(HttpStatus.CREATED).body(CustomerCreatedResponse.builder().name(savedCustomer.getName()).build().message());
    }
  }

  @Override
  public ResponseEntity<List<CustomerReceiveProductResponse>> receiveCustomerProducts(CustomerProductRequest customerProductRequest, Long customerId) {
    CustomerEntity customerInfo = customerRepository.findById(customerId).orElseThrow(() -> new CustomerErrorResponse(CustomerErrorResponseType.CUSTOMER_NOT_FOUND));
    List<CustomerReceiveProductResponse> customerReceiveProductResponses = new ArrayList<>();
    customerProductRequest.getProducts().stream().forEach(requestCustomerId -> {
      Optional<ProductEntity> productInfo = productRepository.findById(requestCustomerId);
      if (productInfo.isPresent()) {
        Long exitsProductStock = paymentRepository.existsProductStock(productInfo.get().getId());
        if (exitsProductStock != 0) { //urun stokta varsa ekle
          paymentRepository.save(PaymentEntity.builder()
              .product(productInfo.get())
              .customer(customerInfo)
              .startDate(LocalDateTime.now())
              .orderStatus(OrderStatus.PENDING)
              .build());
          customerReceiveProductResponses.add(CustomerReceiveProductResponse.builder().productName(productInfo.get().getName()).build());

        }
      }
    });
    return ResponseEntity.status(HttpStatus.OK).body(customerReceiveProductResponses);
  }
}

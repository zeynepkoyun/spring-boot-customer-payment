package com.example.customerpayment.common.error;

import com.example.customerpayment.customer.domain.error.CustomerErrorResponse;
import com.example.customerpayment.payment.domain.error.PaymentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomerErrorResponse.class)
  public ResponseEntity<CustomerErrorResponse> handleCustomException(CustomerErrorResponse errorResponse) {
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(PaymentErrorResponse.class)
  public ResponseEntity<PaymentErrorResponse> handlePaymentErrorResponse(PaymentErrorResponse errorResponse) {
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

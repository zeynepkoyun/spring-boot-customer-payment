package com.example.customerpayment.product.service;

import com.example.customerpayment.product.model.request.ProductAddRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {
  ResponseEntity<String> createProduct(ProductAddRequest productAddRequest);

}

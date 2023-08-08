package com.example.customerpayment.product.controller;

import com.example.customerpayment.product.model.request.ProductAddRequest;
import com.example.customerpayment.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @PostMapping
  public ResponseEntity<String> createCustomerWithOrders(@RequestBody ProductAddRequest productAddRequest) {
    return productService.createProduct(productAddRequest);
  }
}

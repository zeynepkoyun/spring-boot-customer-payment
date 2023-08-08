package com.example.customerpayment.product.service;

import com.example.customerpayment.product.domain.ProductEntity;
import com.example.customerpayment.product.domain.error.ProductErrorResponse;
import com.example.customerpayment.product.domain.error.ProductErrorResponseType;
import com.example.customerpayment.product.model.request.ProductAddRequest;
import com.example.customerpayment.product.model.response.ProductAddResponse;
import com.example.customerpayment.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
  private ProductRepository productRepository;

  @Override
  public ResponseEntity<String> createProduct(ProductAddRequest productAddRequest) {
    Optional<ProductEntity> productInfo = productRepository.findByName(productAddRequest.getName());
    if (productInfo.isPresent()) {
      throw new ProductErrorResponse(ProductErrorResponseType.PRODUCT_NAME_ALREADY_EXISTS);
    } else {
      productRepository.save(ProductEntity.builder()
          .price(productAddRequest.getPrice())
          .name(productAddRequest.getName())
          .stockNumber(productAddRequest.getStockNumber())
          .build());

      return ResponseEntity.status(HttpStatus.OK).body(ProductAddResponse.builder().name(productAddRequest.getName()).build().message());
    }

  }
}

package com.example.customerpayment.product.repository;

import com.example.customerpayment.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
  @Query(value = "SELECT p FROM ProductEntity  p WHERE p.name=:productName")
  Optional<ProductEntity> findByName(String productName);


}

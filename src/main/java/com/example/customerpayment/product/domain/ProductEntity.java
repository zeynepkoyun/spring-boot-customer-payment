package com.example.customerpayment.product.domain;

import com.example.customerpayment.payment.domain.PaymentEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "price")
  private Double price;
  @Column(name = "stock_number")
  @Min(1)
  private Integer stockNumber;
  @OneToMany(mappedBy = "product")
  private Set<PaymentEntity> orders = new HashSet<>();
}

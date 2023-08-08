package com.example.customerpayment.customer.domain;

import com.example.customerpayment.payment.domain.PaymentEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "customer_number")
  private String customerNumber;

  @Column(name = "cart_number")
  private String cartNumber;

  @Column(name = "email")
  @Email
  private String email;

  @OneToMany(mappedBy = "customer")
  private Set<PaymentEntity> orders = new HashSet<>();
}

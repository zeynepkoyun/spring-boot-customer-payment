package com.example.customerpayment.customer.repository;

import com.example.customerpayment.customer.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  @Query(value = "SELECT COUNT(*) FROM CustomerEntity  c WHERE c.cartNumber =:cartNumber or c.email =:email or c.customerNumber=:customerNumber")
  Integer findCustomerByCartNumberAndEmailAndCustomerNumber(String cartNumber,String email,String customerNumber);
}

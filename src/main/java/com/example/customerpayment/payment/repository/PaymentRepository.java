package com.example.customerpayment.payment.repository;

import com.example.customerpayment.customer.domain.CustomerEntity;
import com.example.customerpayment.payment.domain.PaymentEntity;
import com.example.customerpayment.payment.domain.PaymentType;
import com.example.customerpayment.product.domain.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
  @Query(value = "SELECT SUM(product.price) FROM PaymentEntity  payment JOIN ProductEntity  product ON product.id=payment.product.id where payment.customer=:customerNumber")
  Double findCustomersPaymentAmount(CustomerEntity customerNumber);

  //birden fazla alan update edildigi icin kullanılmadı
  @Modifying
  @Transactional
  @Query(value = "UPDATE PaymentEntity payment SET payment.endDate=:endDate,payment.product.stockNumber=payment.product.stockNumber-1  where payment.customer=:customerNumber")
  void updateEndDateByCustomerNumber(@Param("endDate") LocalDateTime endDate, @Param("customerNumber") CustomerEntity customerNumber);

  @Query(value = "SELECT payment FROM PaymentEntity  payment JOIN ProductEntity  product ON product.id=payment.product.id where payment.customer=:customerNumber and payment.orderStatus='PENDING'")
  List<PaymentEntity> findCustomerPaymentList(CustomerEntity customerNumber);

//  @Query(value = "SELECT product.name,product.price,payment.startDate,payment.endDate,case when payment.endDate is null then 'Not Paid' else 'Paid' end as status FROM PaymentEntity  payment JOIN ProductEntity  product ON product.id=payment.product.id")
  @Query(value = "SELECT product.name,product.price,payment.startDate,payment.endDate,payment.orderStatus FROM PaymentEntity  payment JOIN ProductEntity  product ON product.id=payment.product.id")
  List<Object[]> findAllPaymentList();

  @Query(value = "SELECT payment FROM PaymentEntity payment WHERE payment.customer=:customerNumber")
  List<PaymentEntity> findPaymentEntitiesByCustomer(CustomerEntity customerNumber);

  @Query(value = "SELECT count(*) FROM ProductEntity  product where product.id=:productId and product.stockNumber > (SELECT COUNT(*) FROM PaymentEntity  payment WHERE payment.product.id=:productId and payment.orderStatus='PENDING')")
  Long existsProductStock(Long productId);

}

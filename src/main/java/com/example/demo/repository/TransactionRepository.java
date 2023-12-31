package com.example.demo.repository;

import com.example.demo.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<CustomerTransaction, String> {
    List<CustomerTransaction> findByCustomerName(String customerName);
}

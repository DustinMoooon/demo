package com.example.demo.service;

import com.example.demo.entity.CustomerTransaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class DataInitializerService {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostConstruct
    public void initializeData() {
        // Sample transactions for customer with ID "sampleCustomer"
        CustomerTransaction transaction1 = new CustomerTransaction("Java", "1", 120, new Date());
        CustomerTransaction transaction2 = new CustomerTransaction("Python", "2", 80, new Date());
        CustomerTransaction transaction3 = new CustomerTransaction("C++", "3", 270, new Date());

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);
    }
}

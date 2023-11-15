package com.example.demo.service;

import com.example.demo.entity.CustomerTransaction;
import com.example.demo.repository.TransactionRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RewardService {
    @Autowired
    private TransactionRepository transactionRepository;

    public int calculateRewardPoints(String customerName) {
        List<CustomerTransaction> transactions = transactionRepository.findByCustomerName(customerName);
        int totalRewardPoints = 0;

        for (CustomerTransaction transaction : transactions) {
            int transactionAmount = transaction.getAmount();

            if (transactionAmount > 100) {
                totalRewardPoints += (transactionAmount - 100) * 2 + 50;
            } else if (transactionAmount > 50) {
                totalRewardPoints += transactionAmount - 50;
            }
        }

        return totalRewardPoints;
    }

    public void addTransaction(CustomerTransaction transaction) {
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(new Date());
        }
        transactionRepository.save(transaction);
    }

    public void updateTransaction(String transactionId, CustomerTransaction updatedTransaction) {
        Optional<CustomerTransaction> existingTransactionOptional = transactionRepository.findById(transactionId);

        if (existingTransactionOptional.isPresent()) {
            // Transaction found, update its properties
            CustomerTransaction existingTransaction = existingTransactionOptional.get();

            // Update only the properties that should be modified
            existingTransaction.setCustomerName(updatedTransaction.getCustomerName());
            existingTransaction.setTransactionId(updatedTransaction.getTransactionId());
            existingTransaction.setAmount(updatedTransaction.getAmount());
            existingTransaction.setTransactionDate(updatedTransaction.getTransactionDate());

            // Save the updated transaction to the repository
            transactionRepository.save(existingTransaction);
        } else {
            // Transaction with the given ID not found, handle accordingly (throw exception or return an error message)
            // For simplicity, let's throw an exception in this example
            throw new EntityNotFoundException("Transaction not found with ID: " + transactionId);
        }
    }

    public void deleteTransaction(String transactionId) {
        Optional<CustomerTransaction> existingTransactionOptional = transactionRepository.findById(transactionId);

        if (existingTransactionOptional.isPresent()) {
            // Transaction found, delete it
            transactionRepository.deleteById(transactionId);
        } else {
            // Transaction with the given ID not found, handle accordingly (throw exception or return an error message)
            // For simplicity, let's throw an exception in this example
            throw new EntityNotFoundException("Transaction not found with ID: " + transactionId);
        }
    }
    private void validateTransactionData(CustomerTransaction transaction) {
        if (StringUtils.isEmpty(transaction.getCustomerName())) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
    }
}

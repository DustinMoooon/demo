package com.example.demo.contoller;

import com.example.demo.entity.CustomerTransaction;
import com.example.demo.service.RewardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private RewardService rewardService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Integer> getRewardPoints(@PathVariable String customerId) {
        int rewardPoints = rewardService.calculateRewardPoints(customerId);
        return new ResponseEntity<>(rewardPoints, HttpStatus.OK);
    }
    @PostMapping("/addTransaction")
    public ResponseEntity<String> addTransaction(@RequestBody CustomerTransaction transaction) {

        rewardService.addTransaction(transaction);
        return new ResponseEntity<>("Transaction added successfully", HttpStatus.CREATED);
    }
    @PutMapping("/updateTransaction/{transactionId}")
    public ResponseEntity<String> updateTransaction(
            @PathVariable String transactionId,
            @RequestBody CustomerTransaction updatedTransaction) {
        // Add validation and error handling as needed
        rewardService.updateTransaction(transactionId, updatedTransaction);
        return new ResponseEntity<>("Transaction updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/deleteTransaction/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String transactionId) {
        // Add validation and error handling as needed
        rewardService.deleteTransaction(transactionId);
        return new ResponseEntity<>("Transaction deleted successfully", HttpStatus.OK);
    }
}
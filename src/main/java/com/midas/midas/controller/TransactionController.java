package com.midas.midas.controller;

import com.midas.midas.model.Transaction;
import com.midas.midas.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{userId}")
    public Transaction addTransaction(@PathVariable Long userId, @RequestParam String description, @RequestParam BigDecimal amount, @RequestParam String type) {
        return transactionService.addTransaction(userId, description, amount, type);
    }
}

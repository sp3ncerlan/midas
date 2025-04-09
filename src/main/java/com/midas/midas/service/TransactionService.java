package com.midas.midas.service;

import com.midas.midas.model.Transaction;
import com.midas.midas.model.User;
import com.midas.midas.repository.TransactionRepository;
import com.midas.midas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction addTransaction(Long userId, String description, BigDecimal amount, String type) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        Transaction transaction = new Transaction();

        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setUser(user);

        return transactionRepository.save(transaction);
    }
}

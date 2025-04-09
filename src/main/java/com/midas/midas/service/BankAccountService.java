package com.midas.midas.service;

import com.midas.midas.model.BankAccount;
import com.midas.midas.model.User;
import com.midas.midas.repository.BankAccountRepository;
import com.midas.midas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserRepository userRepository;

    public BankAccount addBankAccount(Long userId, String bankName, BigDecimal balance) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        BankAccount bankAccount = new BankAccount();

        bankAccount.setBankName(bankName);
        bankAccount.setBalance(balance);
        bankAccount.setUser(user);

        return bankAccountRepository.save(bankAccount);
    }
}

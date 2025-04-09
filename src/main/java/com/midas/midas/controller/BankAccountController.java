package com.midas.midas.controller;

import com.midas.midas.model.BankAccount;
import com.midas.midas.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/{userId}")
    public BankAccount addBankAccount(@PathVariable Long userId, @RequestParam String bankName, @RequestParam BigDecimal balance) {
        return bankAccountService.addBankAccount(userId, bankName, balance);
    }
}

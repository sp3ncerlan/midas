package com.midas.midas.service;

import com.midas.midas.model.Asset;
import com.midas.midas.model.BankAccount;
import com.midas.midas.model.User;
import com.midas.midas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // User Registration & Login
    public User registerUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEmail(email);

        return userRepository.save(user);
    }

    public User authenticate(String username, String rawPassword) {
        User user = (User) userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
    }

    // Total Asset Methods
    public BigDecimal calculateTotalAssets(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        BigDecimal bankTotal = user.getBankAccounts()
                .stream()
                .map(BankAccount::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal assetTotal = user.getAssets() != null ?
                user.getAssets().stream()
                    .map(Asset::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add) : BigDecimal.ZERO;

        return bankTotal.add(assetTotal);
    }
}

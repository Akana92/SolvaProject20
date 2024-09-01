package com.example.solva.service;

import com.example.solva.User;
import com.example.solva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final Random random = new SecureRandom();

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(String name, String email, String currency, String password) {
        // Генерация случайного 10-значного номера банковского счета
        Integer bankAccount = generateRandomBankAccount();

        // Генерация случайной суммы до 100,000 с округлением до сотых
        BigDecimal amount = generateRandomAmount();

        User newUser = User.builder()
                .name(name)
                .email(email)
                .bankAccount(bankAccount)
                .amount(amount)
                .currency(currency)
                .password(password)
                .build();
        System.out.println("Saving user: " + newUser);
        return userRepository.save(newUser);
    }

    private Integer generateRandomBankAccount() {
        // Генерация 10-значного числа
        return 1000000000 + random.nextInt(900000000);
    }

    private BigDecimal generateRandomAmount() {
        // Генерация суммы до 100,000 с округлением до сотых
        double randomAmount = random.nextDouble() * 100000;
        return BigDecimal.valueOf(randomAmount).setScale(2, RoundingMode.HALF_EVEN);
    }

    public List<User> findAllOtherUsers(Integer currentUserId) {
        return userRepository.findAll().stream()
                .filter(user -> !user.getId().equals(currentUserId))
                .collect(Collectors.toList());
    }


    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}

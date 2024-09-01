package com.example.solva.service;

import com.example.solva.Transaction;
import com.example.solva.User;
import com.example.solva.repository.TransactionRepository;
import com.example.solva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transferMoney(Integer fromUserId, Integer toUserId, BigDecimal amount, String currency) {
        User sender = userRepository.findById(fromUserId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(toUserId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (sender.getAmount().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        sender.setAmount(sender.getAmount().subtract(amount));
        receiver.setAmount(receiver.getAmount().add(amount));

        userRepository.save(sender);
        userRepository.save(receiver);

        Transaction transaction = new Transaction();
        transaction.setAccountFrom(sender);
        transaction.setAccountTo(receiver);
        transaction.setSum(amount);
        transaction.setCurrency_shortname(currency);
        transaction.setDateTime(ZonedDateTime.now());
        transactionRepository.save(transaction);
    }

    public List<Transaction> findTransactionsByUser(Integer userId) {
        return transactionRepository.findByAccountFromIdOrAccountToId(userId, userId);
    }
}

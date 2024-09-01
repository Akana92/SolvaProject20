package com.example.solva.repository;

import com.example.solva.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountFromIdOrAccountToId(Integer accountFromId, Integer accountToId);
}

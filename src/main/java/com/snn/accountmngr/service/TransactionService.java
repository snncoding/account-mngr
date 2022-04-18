package com.snn.accountmngr.service;

import com.snn.accountmngr.model.Transaction;
import com.snn.accountmngr.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;

    public void create(Transaction transaction) {
        repository.save(transaction);
    }
}

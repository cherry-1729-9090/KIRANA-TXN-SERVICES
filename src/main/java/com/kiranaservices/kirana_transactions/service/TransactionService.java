package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService{

    @Override
    public Transaction createTransaction(Transaction txn) {
        return null;
    }

    @Override
    public Transaction getTransactionById(String txnId) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }
}

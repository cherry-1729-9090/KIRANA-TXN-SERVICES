package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.dto.TransactionDTO;
import com.kiranaservices.kirana_transactions.model.Transaction;

import java.util.List;

public interface ITransactionService {
    Transaction createTransaction(TransactionDTO txnDTO);
    Transaction getTransactionById(String txnId);
    List<Transaction> getAllTransactions();
}

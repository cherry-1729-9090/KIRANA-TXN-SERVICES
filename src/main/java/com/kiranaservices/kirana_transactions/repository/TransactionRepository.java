package com.kiranaservices.kirana_transactions.repository;

import com.kiranaservices.kirana_transactions.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByUserId(String userId);

    List<Transaction> findByTxnType(String txnType);

    List<Transaction> findByTxnDateBetween(Date from, Date to);

}

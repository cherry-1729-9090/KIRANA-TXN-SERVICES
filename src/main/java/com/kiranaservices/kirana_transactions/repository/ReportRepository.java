package com.kiranaservices.kirana_transactions.repository;

import com.kiranaservices.kirana_transactions.enums.TransactionType;
import com.kiranaservices.kirana_transactions.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUserIdAndTxnType(String userId, TransactionType txnType);
    List<Transaction> findByUserIdAndTxnDateBetween(String userId, Date from, Date to);
    List<Transaction> findByUserId(String userId); // New method
}

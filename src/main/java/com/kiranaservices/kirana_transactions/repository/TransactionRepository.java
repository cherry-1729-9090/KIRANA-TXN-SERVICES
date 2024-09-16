package com.kiranaservices.kirana_transactions.repository;

import com.kiranaservices.kirana_transactions.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction,String> {

}

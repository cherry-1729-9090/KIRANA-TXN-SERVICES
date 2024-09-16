package com.kiranaservices.kirana_transactions.controller;

import com.kiranaservices.kirana_transactions.model.Transaction;
import com.kiranaservices.kirana_transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public Transaction createTransaction(@RequestBody Transaction txn){
        Transaction createdTxn = transactionService.createTransaction(txn);
        return createdTxn;
    }

    @GetMapping("/getAll")
    public List<Transaction> getAllTransactions(){
        List<Transaction> txnList = transactionService.getAllTransactions();
        return txnList;
    }



}

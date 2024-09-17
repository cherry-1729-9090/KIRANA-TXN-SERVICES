package com.kiranaservices.kirana_transactions.controller;

import com.kiranaservices.kirana_transactions.dto.TransactionDTO;
import com.kiranaservices.kirana_transactions.model.Transaction;
import com.kiranaservices.kirana_transactions.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final ITransactionService transactionService;

    @Autowired
    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public Transaction createTransaction(@RequestBody TransactionDTO txnDTO) {
        return transactionService.createTransaction(txnDTO);
    }

    @GetMapping("/get/{txnId}")
    public Transaction getTransaction(@PathVariable String txnId) {
        return transactionService.getTransactionById(txnId);
    }

    @GetMapping("/getAll")
    public List<Transaction> getAllTransactions(@RequestParam String userId) {
        return transactionService.getAllTransactions(userId);
    }
}

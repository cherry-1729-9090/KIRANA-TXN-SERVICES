package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.dto.TransactionDTO;
import com.kiranaservices.kirana_transactions.model.Transaction;
import com.kiranaservices.kirana_transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CurrencyConversionService currencyConversionService) {
        this.transactionRepository = transactionRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
    public Transaction createTransaction(TransactionDTO txnDTO) {
        Transaction txn = new Transaction();
        txn.setTxnId(txnDTO.getTxnId());

        // If the currency is not INR, convert the amount to INR
        System.out.println(txnDTO);
        double convertedAmount = txnDTO.getCurrencyType().getCurrencyCode().equals("INR")
                ? txnDTO.getTxnAmount()
                : currencyConversionService.convertToINR(txnDTO.getCurrencyType().getCurrencyCode(), txnDTO.getTxnAmount());

        txn.setTxnAmount(convertedAmount);
        txn.setTxnDate(txnDTO.getTxnDate());
        txn.setCurrencyType(txnDTO.getCurrencyType());
        txn.setUserId(txnDTO.getUserId());
        txn.setTxnType(txnDTO.getTxnType());
        txn.setTxnDetails(txnDTO.getTxnDetails());
        return transactionRepository.save(txn);
    }

    @Override
    public Transaction getTransactionById(String txnId) {
        return transactionRepository.findById(txnId).orElse(null);
    }

    @Override
    public List<Transaction> getAllTransactions(String userId) {
        return transactionRepository.findByUserId(userId);
    }
}

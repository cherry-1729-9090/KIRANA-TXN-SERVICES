package com.kiranaservices.kirana_transactions.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Report{
    private List<Transaction> creditTransactions;
    private List<Transaction> debitTransactions;
    private Double totalCreditAmount;
    private Double totalDebitAmount;
    private Double netFlow;

    public Report() {
        this.totalCreditAmount = 0.0;
        this.totalDebitAmount = 0.0;
        this.netFlow = 0.0;
    }

    public void calculateTotals() {
        for (Transaction txn : creditTransactions) {
            totalCreditAmount += txn.getTxnAmount();
        }
        for (Transaction txn : debitTransactions) {
            totalDebitAmount += txn.getTxnAmount();
        }
        netFlow = totalCreditAmount - totalDebitAmount;
    }
}

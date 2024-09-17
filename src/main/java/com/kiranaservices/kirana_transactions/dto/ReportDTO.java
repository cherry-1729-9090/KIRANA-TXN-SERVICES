package com.kiranaservices.kirana_transactions.dto;

import com.kiranaservices.kirana_transactions.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportDTO {
    private List<Transaction> creditTransactions;
    private List<Transaction> debitTransactions;
    private Double totalCreditAmount;
    private Double totalDebitAmount;
    private Double netFlow;
}

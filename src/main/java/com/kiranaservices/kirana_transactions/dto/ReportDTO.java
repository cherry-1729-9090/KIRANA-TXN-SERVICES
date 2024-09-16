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
    private Integer totalCreditAmount;
    private Integer totalDebitAmount;
    private Integer netFlow;
}

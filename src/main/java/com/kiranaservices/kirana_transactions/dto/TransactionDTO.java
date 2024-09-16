package com.kiranaservices.kirana_transactions.dto;

import com.kiranaservices.kirana_transactions.enums.TransactionType;

import java.util.Currency;
import java.util.Date;

public class TransactionDTO {
    private String txnId;
    private Integer txnAmount;
    private Date txnDate;
    private Currency currencyType;
    private String userId;
    private TransactionType txnType;
    private String txnDetails;
}

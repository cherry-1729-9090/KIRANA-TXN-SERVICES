package com.kiranaservices.kirana_transactions.dto;

import com.kiranaservices.kirana_transactions.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.Date;

@Getter
@Setter
public class TransactionDTO {
    private String txnId;
    private Double txnAmount;
    private Date txnDate;
    private Currency currencyType;
    private String userId;
    private TransactionType txnType;
    private String txnDetails;
}


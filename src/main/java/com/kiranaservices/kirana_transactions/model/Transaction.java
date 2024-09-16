package com.kiranaservices.kirana_transactions.model;

import com.kiranaservices.kirana_transactions.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Currency;
import java.util.Date;

@Getter
@Setter


@Document(collection = "Transaction")
public class Transaction {
    @Id
    private String txnId;
    private Integer txnAmount;
    private Date txnDate;
    private Currency currencyType;
    private String userId;
    private TransactionType txnType;
    private String txnDetails;
}

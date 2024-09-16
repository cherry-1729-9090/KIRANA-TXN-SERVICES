package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.enums.TransactionType;

import java.util.Date;
import java.util.List;
public interface IReportService {
    List<Object> filterTransactions(String userId, TransactionType txnType,String period);

    List<Object> getTransactionsInDates(Date from,Date to);
}

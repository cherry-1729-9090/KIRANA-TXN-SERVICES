package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.enums.TransactionType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportService implements IReportService{

    @Override
    public List<Object> filterTransactions(String userId, TransactionType txnType, String period) {
        return null;
    }

    @Override
    public List<Object> getTransactionsInDates(Date from, Date to) {
        return null;
    }
}

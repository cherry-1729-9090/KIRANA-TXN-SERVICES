package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.enums.TransactionType;
import com.kiranaservices.kirana_transactions.model.Report;

import java.util.Date;
import java.util.List;
public interface IReportService {
    Report getReportOnPeriod(String userId, TransactionType txnType, String period);


    Report getReportInDates(String userId,Date from,Date to);

}

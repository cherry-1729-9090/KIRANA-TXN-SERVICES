package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.model.Report;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

public interface IReportService {
    Report getReportOnPeriod(String userId, String period);

    Report getReportInDates(String userId, OffsetDateTime from, OffsetDateTime to);
}

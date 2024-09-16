package com.kiranaservices.kirana_transactions.controller;

import com.kiranaservices.kirana_transactions.dto.ReportDTO;
import com.kiranaservices.kirana_transactions.enums.TransactionType;
import com.kiranaservices.kirana_transactions.model.Report;
import com.kiranaservices.kirana_transactions.service.IReportService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/onPeriod")
    public Report getReportOnPeriod(
            @RequestParam String userId,
            @RequestParam TransactionType type,
            @RequestParam String period) {
        return reportService.getReportOnPeriod(userId, type, period);
    }

    @GetMapping("/inDates")
    public Report getReportInDates(
            @RequestParam String userId,
            @RequestParam Date from,
            @RequestParam Date to) {
        return  reportService.getReportInDates(userId, from, to);
    }
}

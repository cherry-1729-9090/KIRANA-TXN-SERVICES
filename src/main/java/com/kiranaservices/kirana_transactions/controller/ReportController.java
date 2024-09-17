package com.kiranaservices.kirana_transactions.controller;

import com.kiranaservices.kirana_transactions.model.Report;
import com.kiranaservices.kirana_transactions.service.IReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

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
            @RequestParam String period) {
        return reportService.getReportOnPeriod(userId, period);
    }

    @GetMapping("/inDates")
    public Report getReportInDates(
            @RequestParam String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to) {
        return reportService.getReportInDates(userId, from, to);
    }
}

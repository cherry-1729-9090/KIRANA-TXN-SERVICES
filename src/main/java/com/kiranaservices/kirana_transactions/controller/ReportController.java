package com.kiranaservices.kirana_transactions.controller;

import com.kiranaservices.kirana_transactions.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;
    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }



}

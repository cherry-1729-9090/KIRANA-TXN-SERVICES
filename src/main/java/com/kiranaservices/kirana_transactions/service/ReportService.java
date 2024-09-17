package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.dto.ReportDTO;
import com.kiranaservices.kirana_transactions.enums.TransactionType;
import com.kiranaservices.kirana_transactions.model.Report;
import com.kiranaservices.kirana_transactions.model.Transaction;
import com.kiranaservices.kirana_transactions.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReportService implements IReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report getReportOnPeriod(String userId, String period) {
        List<Transaction> transactions;

        transactions = reportRepository.findByUserId(userId);

        ReportDTO reportDTO = createReportDTO(transactions);
        return convertToReport(reportDTO);
    }

    @Override
    public Report getReportInDates(String userId, OffsetDateTime from, OffsetDateTime to) {
        // Convert OffsetDateTime to Date if needed for repository query
        Date fromDate = java.sql.Timestamp.valueOf(from.toLocalDateTime());
        Date toDate = java.sql.Timestamp.valueOf(to.toLocalDateTime());

        List<Transaction> transactions = reportRepository.findByUserIdAndTxnDateBetween(userId, fromDate, toDate);
        ReportDTO reportDTO = createReportDTO(transactions);

        return convertToReport(reportDTO);
    }

    private ReportDTO createReportDTO(List<Transaction> transactions) {
        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setCreditTransactions(transactions.stream()
                .filter(txn -> TransactionType.CREDIT.equals(txn.getTxnType()))
                .toList());
        reportDTO.setDebitTransactions(transactions.stream()
                .filter(txn -> TransactionType.DEBIT.equals(txn.getTxnType()))
                .toList());

        reportDTO.setTotalCreditAmount(reportDTO.getCreditTransactions().stream()
                .mapToDouble(Transaction::getTxnAmount)
                .sum());
        reportDTO.setTotalDebitAmount(reportDTO.getDebitTransactions().stream()
                .mapToDouble(Transaction::getTxnAmount)
                .sum());
        reportDTO.setNetFlow(reportDTO.getTotalCreditAmount() - reportDTO.getTotalDebitAmount());

        return reportDTO;
    }

    private Report convertToReport(ReportDTO reportDTO) {
        Report report = new Report();
        report.setCreditTransactions(reportDTO.getCreditTransactions());
        report.setDebitTransactions(reportDTO.getDebitTransactions());
        report.setTotalCreditAmount(reportDTO.getTotalCreditAmount());
        report.setTotalDebitAmount(reportDTO.getTotalDebitAmount());
        report.setNetFlow(reportDTO.getNetFlow());
        return report;
    }
}

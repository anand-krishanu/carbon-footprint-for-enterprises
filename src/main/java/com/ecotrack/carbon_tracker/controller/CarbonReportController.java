package com.ecotrack.carbon_tracker.controller;

import com.ecotrack.carbon_tracker.dto.CarbonSummaryDTO;
import com.ecotrack.carbon_tracker.service.CarbonReportService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class CarbonReportController {

    @Autowired
    CarbonReportService carbonReportService;

    @GetMapping
    public List<CarbonSummaryDTO> getCarbonSummary () {
        return carbonReportService.generateCarbonSummary();
    }

    @GetMapping("/carbon-report/pdf")
    public ResponseEntity<byte[]> getCarbonReportPdf() throws DocumentException, IOException {
        byte[] pdfBytes = carbonReportService.generateCarbonSummaryPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition", "inline; filename=carbon-summary-report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

}
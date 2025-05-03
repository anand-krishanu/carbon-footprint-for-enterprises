package com.ecotrack.carbon_tracker.controller;

import com.ecotrack.carbon_tracker.dto.CarbonSummaryDTO;
import com.ecotrack.carbon_tracker.service.CarbonReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

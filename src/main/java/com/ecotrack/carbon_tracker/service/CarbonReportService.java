package com.ecotrack.carbon_tracker.service;

import com.ecotrack.carbon_tracker.dto.CarbonSummaryDTO;
import com.ecotrack.carbon_tracker.entity.EmissionRecord;
import com.ecotrack.carbon_tracker.repository.DepartmentRepository;
import com.ecotrack.carbon_tracker.repository.EmissionRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarbonReportService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmissionRecordRepository emissionRecordRepository;

    public List<CarbonSummaryDTO> generateCarbonSummary () {
        LocalDate startOfYear  = LocalDate.now().withDayOfYear(1);
        LocalDate endOfYear = LocalDate.now().withMonth(12).withDayOfMonth(31);

        return departmentRepository.findAll().stream().map( dept -> {
            List<EmissionRecord> records = emissionRecordRepository.findByDepartmentAndDateBetween(dept, startOfYear, endOfYear);
            double totalEmissions = records.stream().mapToDouble(EmissionRecord::getCo2Amount).sum();
            boolean targetExceeded = totalEmissions > dept.getAnnualCarbonTarget();

            return new CarbonSummaryDTO(
                    dept.getName(),
                    totalEmissions,
                    dept.getAnnualCarbonTarget(),
                    targetExceeded
            );
        }).collect(Collectors.toList());
    }
}
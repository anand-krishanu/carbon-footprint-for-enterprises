package com.ecotrack.carbon_tracker.service;

import com.ecotrack.carbon_tracker.dto.CarbonSummaryDTO;
import com.ecotrack.carbon_tracker.entity.Department;
import com.ecotrack.carbon_tracker.entity.EmissionRecord;
import com.ecotrack.carbon_tracker.repository.DepartmentRepository;
import com.ecotrack.carbon_tracker.repository.EmissionRecordRepository;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public byte[] generateCarbonSummaryPdf() throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        document.add(new Paragraph("Carbon Emission Summary Report"));
        document.add(Chunk.NEWLINE);

        LocalDate startOfYear  = LocalDate.now().withDayOfYear(1);
        LocalDate endOfYear = LocalDate.now().withMonth(12).withDayOfMonth(31);

        List<Department> departments = departmentRepository.findAll();

        for (Department department: departments) {
            List<EmissionRecord> records = emissionRecordRepository.findByDepartmentAndDateBetween(department, startOfYear, endOfYear);
            double totalEmissions = records.stream().mapToDouble(EmissionRecord::getCo2Amount).sum();
            boolean targetExceeded = totalEmissions > department.getAnnualCarbonTarget();

            document.add(new Paragraph("Department: " + department.getName()));
            document.add(new Paragraph("Total Emissions: " + totalEmissions + " CO₂e"));
            document.add(new Paragraph("Annual Carbon Target: " + department.getAnnualCarbonTarget()));
            document.add(new Paragraph("Target Exceeded: " + (targetExceeded ? "Yes" : "No")));
            document.add(Chunk.NEWLINE);
        }

        document.close();

        return byteArrayOutputStream.toByteArray();
    }

}
package com.ecotrack.carbon_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarbonSummaryDTO {
    private String departmentName;
    private Double totalEmission;
    private Double annualAmount;
    private boolean targetExceeded;
}

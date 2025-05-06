package com.ecotrack.carbon_tracker;

import com.ecotrack.carbon_tracker.controller.CarbonReportController;
import com.ecotrack.carbon_tracker.dto.CarbonSummaryDTO;
import com.ecotrack.carbon_tracker.service.CarbonReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

public class CarbonReportControllerTests {

    @Mock
    private CarbonReportService carbonReportService;

    @InjectMocks
    private CarbonReportController carbonReportController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(carbonReportController).build();
    }

    @Test
    public void testGetCarbonSummary() throws Exception {

        List<CarbonSummaryDTO> mockSummary = new ArrayList<>();
        mockSummary.add(new CarbonSummaryDTO("Department A", 1000.0, 1200.0, true));
        mockSummary.add(new CarbonSummaryDTO("Department B", 800.0, 1200.0, false));

        when(carbonReportService.generateCarbonSummary()).thenReturn(mockSummary);

        mockMvc.perform(get("/api/report"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].departmentName").value("Department A"))
                .andExpect(jsonPath("$[0].totalEmission").value(1000.0))
                .andExpect(jsonPath("$[0].annualAmount").value(1200.0))
                .andExpect(jsonPath("$[0].targetExceeded").value(true))
                .andExpect(jsonPath("$[1].departmentName").value("Department B"))
                .andExpect(jsonPath("$[1].totalEmission").value(800.0))
                .andExpect(jsonPath("$[1].annualAmount").value(1200.0))
                .andExpect(jsonPath("$[1].targetExceeded").value(false));
    }

    @Test
    public void testGetCarbonReportPdf() throws Exception {

        byte[] mockPdfBytes = new byte[]{37, 80, 68, 70, 45, 49, 46, 52};
        when(carbonReportService.generateCarbonSummaryPdf()).thenReturn(mockPdfBytes);

        mockMvc.perform(get("/api/report/carbon-report/pdf"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_PDF))
                .andExpect(header().string("Content-Disposition", "inline; filename=carbon-summary-report.pdf"))
                .andExpect(content().bytes(mockPdfBytes));
    }
}

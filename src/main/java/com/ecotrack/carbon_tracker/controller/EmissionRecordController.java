package com.ecotrack.carbon_tracker.controller;

import com.ecotrack.carbon_tracker.entity.EmissionRecord;
import com.ecotrack.carbon_tracker.service.EmissionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/record")
public class EmissionRecordController {

    @Autowired
    EmissionRecordService emissionRecordService;

    @GetMapping
    public ResponseEntity<?> allRecords () {
        return ResponseEntity.ok(emissionRecordService.getAllEmissionRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecordById (@PathVariable Long id) {
        return ResponseEntity.ok(emissionRecordService.getEmissionRecordById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addRecord (@RequestBody EmissionRecord record) {
        return ResponseEntity.ok(emissionRecordService.createEmissionRecord(record));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> updateRecord (@PathVariable Long id, @RequestBody EmissionRecord record) {
        return ResponseEntity.ok(emissionRecordService.updateEmissionRecord(id, record));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deleteRecord (@PathVariable Long id){
        emissionRecordService.deleteEmissionRecord(id);

        return ResponseEntity.ok("Record Deleted!");
    }
}

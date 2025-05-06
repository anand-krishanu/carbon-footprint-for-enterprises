package com.ecotrack.carbon_tracker.controller;

import com.ecotrack.carbon_tracker.entity.EmissionSource;
import com.ecotrack.carbon_tracker.service.EmissionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/source")
public class EmissionSourceController {

    @Autowired
    EmissionSourceService emissionSourceService;

    @GetMapping
    public ResponseEntity<?> allRecords () {
        return ResponseEntity.ok(emissionSourceService.getAllEmissionSources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecordById (@PathVariable Long id) {
        return ResponseEntity.ok(emissionSourceService.getEmissionSourceById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addRecord (@RequestBody EmissionSource source) {
        return ResponseEntity.ok(emissionSourceService.createEmissionSource(source));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> updateRecord (@PathVariable Long id, @RequestBody EmissionSource source) {
        return ResponseEntity.ok(emissionSourceService.updateEmissionSource(id, source));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deleteRecord (@PathVariable Long id){
        emissionSourceService.deleteEmissionSource(id);

        return ResponseEntity.ok("Record Deleted!");
    }
}

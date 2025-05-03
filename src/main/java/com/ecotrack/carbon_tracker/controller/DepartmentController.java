package com.ecotrack.carbon_tracker.controller;

import com.ecotrack.carbon_tracker.entity.Department;
import com.ecotrack.carbon_tracker.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAllDepartments () {
        List<Department> allDepartments = departmentService.getAllDepartments();

        return ResponseEntity.ok(allDepartments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById (@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> saveDepartment (@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    @PutMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> updateDepartment (@PathVariable Long id, @RequestBody Department department) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, department));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deleteDepartment (@PathVariable Long id) {
        departmentService.deleteDepartment(id);

        return ResponseEntity.ok("Expense Deleted!");
    }
}

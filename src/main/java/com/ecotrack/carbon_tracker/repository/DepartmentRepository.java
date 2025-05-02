package com.ecotrack.carbon_tracker.repository;

import com.ecotrack.carbon_tracker.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

package com.ecotrack.carbon_tracker.repository;

import com.ecotrack.carbon_tracker.entity.EmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmissionRecordRepository extends JpaRepository<EmissionRecord, Long> {
    List<EmissionRecord> findByDepartmentId(Long departmentId);
    List<EmissionRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);
}

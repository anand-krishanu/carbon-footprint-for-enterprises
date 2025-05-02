package com.ecotrack.carbon_tracker.repository;

import com.ecotrack.carbon_tracker.entity.EmissionSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissionSourceRepository extends JpaRepository<EmissionSource, Long> {
}

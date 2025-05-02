package com.ecotrack.carbon_tracker.service;

import com.ecotrack.carbon_tracker.entity.EmissionSource;
import com.ecotrack.carbon_tracker.repository.EmissionSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionSourceService {

    @Autowired
    private EmissionSourceRepository emissionSourceRepository;

    public EmissionSource createEmissionSource(EmissionSource emissionSource) {
        return emissionSourceRepository.save(emissionSource);
    }

    public List<EmissionSource> getAllEmissionSources() {
        return emissionSourceRepository.findAll();
    }

    public EmissionSource getEmissionSourceById(Long id) {
        return emissionSourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Emission source not found"));
    }

    public EmissionSource updateEmissionSource(Long emissionSourceId, EmissionSource updatedEmissionSource) {
        EmissionSource emissionSource = emissionSourceRepository.findById(emissionSourceId).orElseThrow(() -> new RuntimeException("Emission source not found"));

        emissionSource.setSourceName(updatedEmissionSource.getSourceName());
        emissionSource.setEmissionRecords(updatedEmissionSource.getEmissionRecords());

        return emissionSourceRepository.save(emissionSource);
    }

    public void deleteEmissionSource(Long id) {
        emissionSourceRepository.deleteById(id);
    }
}

package com.ecotrack.carbon_tracker.service;

import com.ecotrack.carbon_tracker.entity.EmissionSource;
import com.ecotrack.carbon_tracker.exception.ResourceNotFoundException;
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
        return emissionSourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emission source not found with id: " + id));
    }

    public EmissionSource updateEmissionSource(Long id, EmissionSource updatedEmissionSource) {
        EmissionSource emissionSource = emissionSourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emission source not found with id: " + id));

        emissionSource.setSourceName(updatedEmissionSource.getSourceName());
        emissionSource.setEmissionRecords(updatedEmissionSource.getEmissionRecords());

        return emissionSourceRepository.save(emissionSource);
    }

    public void deleteEmissionSource(Long id) {
        if (!emissionSourceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Emission source not found with id: " + id);
        }
        emissionSourceRepository.deleteById(id);
    }
}

package com.ecotrack.carbon_tracker.service;

import com.ecotrack.carbon_tracker.entity.EmissionRecord;
import com.ecotrack.carbon_tracker.exception.ResourceNotFoundException;
import com.ecotrack.carbon_tracker.repository.EmissionRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionRecordService {

    @Autowired
    private EmissionRecordRepository emissionRecordRepository;

    public EmissionRecord createEmissionRecord(EmissionRecord emissionRecord) {
        return emissionRecordRepository.save(emissionRecord);
    }

    public List<EmissionRecord> getAllEmissionRecords() {
        return emissionRecordRepository.findAll();
    }

    public EmissionRecord getEmissionRecordById(Long id) {
        return emissionRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emission record not found with id: " + id));
    }

    public EmissionRecord updateEmissionRecord(Long id, EmissionRecord updatedRecord) {
        EmissionRecord record = emissionRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emission record not found with id: " + id));

        record.setEmissionType(updatedRecord.getEmissionType());
        record.setDate(updatedRecord.getDate());
        record.setDepartment(updatedRecord.getDepartment());
        record.setEmissionSource(updatedRecord.getEmissionSource());

        return emissionRecordRepository.save(record);
    }

    public void deleteEmissionRecord(Long id) {
        if (!emissionRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Emission record not found with id: " + id);
        }
        emissionRecordRepository.deleteById(id);
    }
}

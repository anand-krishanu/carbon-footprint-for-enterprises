package com.ecotrack.carbon_tracker.service;

import com.ecotrack.carbon_tracker.entity.EmissionRecord;
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
        return emissionRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Emission record not found"));
    }

    public EmissionRecord updateEmissionRecord(Long emissionRecordId, EmissionRecord updatedEmissionRecord) {
        EmissionRecord emissionRecord = emissionRecordRepository.findById(emissionRecordId).orElseThrow(() -> new RuntimeException("Emission record not found"));

        emissionRecord.setEmissionType(updatedEmissionRecord.getEmissionType());
        emissionRecord.setDate(updatedEmissionRecord.getDate());
        emissionRecord.setDepartment(updatedEmissionRecord.getDepartment());
        emissionRecord.setEmissionSource(updatedEmissionRecord.getEmissionSource());

        return emissionRecordRepository.save(emissionRecord);
    }

    public void deleteEmissionRecord(Long id) {
        emissionRecordRepository.deleteById(id);
    }
}

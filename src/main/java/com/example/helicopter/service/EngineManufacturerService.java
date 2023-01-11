package com.example.helicopter.service;

import com.example.helicopter.entity.EngineManufacturer;
import com.example.helicopter.repository.EngineManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineManufacturerService {
    
    @Autowired
    private EngineManufacturerRepository engineManufacturerRepository;

    public List<EngineManufacturer> getEngineManufacturies() {
        return engineManufacturerRepository.findAll();
    }

    public EngineManufacturer getEngineManufacturerById(Long id) {
        return engineManufacturerRepository.findById(id).orElse(null);
    }

    public EngineManufacturer findEngineManufacturerByName(String name) {
        return engineManufacturerRepository.findEngineManufacturerByName(name);
    }

    public EngineManufacturer addEngineManufacturer(EngineManufacturer manufacturer) {
        return engineManufacturerRepository.save(manufacturer);
    }

    public void deleteEngineManufacturer(EngineManufacturer manufacturer) {
        engineManufacturerRepository.delete(manufacturer);
    }
    
}

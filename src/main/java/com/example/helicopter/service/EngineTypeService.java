package com.example.helicopter.service;

import com.example.helicopter.entity.EngineType;
import com.example.helicopter.repository.EngineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineTypeService {
    
    @Autowired
    private EngineTypeRepository engineTypeRepository;

    public List<EngineType> getEngineTypes() {
        return engineTypeRepository.findAll();
    }

    public EngineType getEngineTypeById(Long id) {
        return engineTypeRepository.findById(id).orElse(null);
    }

    public  EngineType findEngineTypeByName(String name) {
        return engineTypeRepository.findEngineTypeByName(name);
    }

    public EngineType addEngineType(EngineType type) {
        return engineTypeRepository.save(type);
    }

    public void deleteEngineType(EngineType type) {
        engineTypeRepository.delete(type);
    }
    
}

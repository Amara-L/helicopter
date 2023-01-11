package com.example.helicopter.service;

import com.example.helicopter.entity.HelicopterManufacturer;
import com.example.helicopter.repository.HelicopterManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelicopterManufacturerService {
    
    @Autowired
    private HelicopterManufacturerRepository helicopterManufacturerRepository;

    public List<HelicopterManufacturer> getHelicopterManufacturies() {
        return helicopterManufacturerRepository.findAll();
    }

    public HelicopterManufacturer getHelicopterManufacturerById(Long id) {
        return helicopterManufacturerRepository.findById(id).orElse(null);
    }

    public HelicopterManufacturer findHelicopterManufacturerByName(String name) {
        return helicopterManufacturerRepository.findHelicopterManufacturerByName(name);
    }

    public HelicopterManufacturer addHelicopterManufacturer(HelicopterManufacturer manufacturer) {
        return helicopterManufacturerRepository.save(manufacturer);
    }

    public void deleteHelicopterManufacturer(HelicopterManufacturer manufacturer) {
        helicopterManufacturerRepository.delete(manufacturer);
    }
    
}

package com.example.helicopter.repository;

import com.example.helicopter.entity.EngineManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineManufacturerRepository extends JpaRepository<EngineManufacturer, Long> {
    EngineManufacturer findEngineManufacturerByName(String name);
}

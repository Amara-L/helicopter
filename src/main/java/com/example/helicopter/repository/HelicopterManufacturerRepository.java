package com.example.helicopter.repository;

import com.example.helicopter.entity.HelicopterManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelicopterManufacturerRepository extends JpaRepository<HelicopterManufacturer, Long> {
    HelicopterManufacturer findHelicopterManufacturerByName(String name);
}

package com.example.helicopter.repository;

import com.example.helicopter.entity.Helicopter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelicopterRepository extends JpaRepository<Helicopter, Long> {
    Helicopter findHelicopterByName(String name);
}

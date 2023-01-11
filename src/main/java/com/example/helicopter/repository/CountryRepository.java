package com.example.helicopter.repository;

import com.example.helicopter.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findCountryByName(String name);
}

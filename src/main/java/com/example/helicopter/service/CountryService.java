package com.example.helicopter.service;

import com.example.helicopter.entity.Country;
import com.example.helicopter.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public Country findCountryByName(String name) {
        return countryRepository.findCountryByName(name);
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountry(Country country) {
        countryRepository.delete(country);
    }

}

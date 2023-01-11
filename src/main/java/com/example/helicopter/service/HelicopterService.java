package com.example.helicopter.service;

import com.example.helicopter.entity.Helicopter;
import com.example.helicopter.repository.HelicopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelicopterService {

    @Autowired
    private HelicopterRepository helicopterRepository;

    public List<Helicopter> getHelicopters() {
        return helicopterRepository.findAll();
    }

    public Helicopter findHelicopterByName(String name) {
        return helicopterRepository.findHelicopterByName(name);
    }

    public List<Helicopter> searchByNameHelicopters(String name) {
        List<Helicopter> helicopters = helicopterRepository.findAll();
        return helicopters.stream().filter(h -> h.getName().contains(name)).collect(Collectors.toList());
    }

    public List<Helicopter> searchByManufHelicopters(String name) {
        List<Helicopter> helicopters = helicopterRepository.findAll();
        return helicopters.stream().filter(h -> h.getManufacturer().getName().contains(name)).collect(Collectors.toList());
    }

    public List<Helicopter> searchByEngineHelicopters(String name) {
        List<Helicopter> helicopters = helicopterRepository.findAll();
        return helicopters.stream().filter(h -> h.getEngineType().getName().contains(name)).collect(Collectors.toList());
    }

    public List<Helicopter> searchByYearHelicopters(Double diap1, Double diap2) {
        List<Helicopter> helicopters = helicopterRepository.findAll();
        return helicopters.stream().filter(h -> diap1 < h.getYear() && diap2 > h.getYear()).collect(Collectors.toList());
    }

    public List<Helicopter> searchByFuelConsumptionHelicopters(Double diap1, Double diap2) {
        List<Helicopter> helicopters = helicopterRepository.findAll();
        return helicopters.stream().filter(h -> diap1 < h.getFuelConsumption() && diap2 > h.getFuelConsumption()).collect(Collectors.toList());
    }

    public List<Helicopter> searchByNumberSeatsHelicopters(Double diap1, Double diap2) {
        List<Helicopter> helicopters = helicopterRepository.findAll();
        return helicopters.stream().filter(h -> diap1 < h.getNumberSeats() && diap2 > h.getNumberSeats()).collect(Collectors.toList());
    }

    public String createText(List<Helicopter> helicopters) {
        if (helicopters == null) return "";
        StringBuilder stringBuilder = new StringBuilder("Количество записей: ").append(helicopters.size())
                .append("\n").append("Список записей:\n------------------------------------------");
        for (Helicopter helicopter : helicopters) {
            stringBuilder
                    .append("\nЗапись ").append(helicopter.getId())
                    .append("\nДанные вертолета:")
                    .append("\n     название:        ")
                    .append(helicopter.getName())
                    .append("\n     год выпуска:     ")
                    .append(helicopter.getYear())
                    .append("\n     количество мест: ")
                    .append(helicopter.getNumberSeats())
                    .append("\n     расход топлива:  ")
                    .append(helicopter.getFuelConsumption())
                    .append(" л/100км")
                    .append("\nДанные двигателя:")
                    .append("\n     название:        ")
                    .append(helicopter.getEngineType().getName())
                    .append("\n     год выпуска:     ")
                    .append(helicopter.getEngineType().getYear())
                    .append("\nДанные производителя двигателя:")
                    .append("\n     наименование:    ")
                    .append(helicopter.getEngineType().getManufacturer().getName())
                    .append("\n     страна:          ")
                    .append(helicopter.getEngineType().getManufacturer().getCountry().getName())
                    .append("\nДанные производителя вертолета:")
                    .append("\n     наименование:    ")
                    .append(helicopter.getManufacturer().getName())
                    .append("\n     страна:          ")
                    .append(helicopter.getManufacturer().getCountry().getName())
                    .append("\n------------------------------------------");
        }
        return stringBuilder.toString();
    }

    public Helicopter getHelicopterById(Long id) {
        return helicopterRepository.findById(id).orElse(null);
    }

    public void deleteHelicopterById(Long id) {
        helicopterRepository.findById(id).ifPresent(helicopter ->
                helicopterRepository.delete(helicopter));
    }

    public Helicopter addHelicopter(Helicopter helicopter) {
        return helicopterRepository.save(helicopter);
    }

}

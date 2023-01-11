package com.example.helicopter.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "helicopter")
public class Helicopter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "manufacturer")
    @ManyToOne
    private HelicopterManufacturer manufacturer;

    @JoinColumn(name = "engine_type")
    @ManyToOne
    private EngineType engineType;

    @Column(name = "year")
    private Integer year;

    @Column(name = "number_seats")
    private Integer numberSeats;

    @Column(name = "fuel_consumption")
    private Double fuelConsumption;

}

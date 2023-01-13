package com.example.helicopter.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
public class HelicopterDTO {

    private Long id;

    private String name;

    private Integer year;

    private Long manufacturerId;

    private String manufacturerName;

    private String manufacturerCountry;

    private Long idEngineType;

    private String nameEngineType;

    private Integer yearEngineType;

    private Long manufacturerIdEngineType;

    private String manufacturerNameEngineType;

    private String manufacturerCountryEngineType;

    private Integer numberSeats;

    private Double fuelConsumption;

}

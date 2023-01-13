package com.example.helicopter.controller;

import com.example.helicopter.Application;
import com.example.helicopter.entity.Country;
import com.example.helicopter.entity.EngineManufacturer;
import com.example.helicopter.entity.EngineType;
import com.example.helicopter.entity.HelicopterDTO;
import com.example.helicopter.entity.HelicopterManufacturer;
import com.example.helicopter.service.HelicopterService;
import com.example.helicopter.entity.Helicopter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HelListController {

    @Autowired
    private HelicopterService helicopterService;

    @FXML
    private Button updateBut;

    @FXML
    private Button retback;

    @Autowired
    private Application application;

    @FXML
    private TableView<HelicopterDTO> helicopterTableView;
    @FXML
    private TableColumn<HelicopterDTO, Long> idColumn;
    @FXML
    private TableColumn<HelicopterDTO, String> nameColumn;
    @FXML
    private TableColumn<HelicopterDTO, Integer> yearColumn;
    @FXML
    private TableColumn<HelicopterDTO, Integer> seatsColumn;
    @FXML
    private TableColumn<HelicopterDTO, Double> fuelColumn;
    @FXML
    private TableColumn<HelicopterDTO, String> manufacturerId;
    @FXML
    private TableColumn<HelicopterDTO, String> manufacturerName;
    @FXML
    private TableColumn<HelicopterDTO, String> manufacturerCount;
    @FXML
    private TableColumn<HelicopterDTO, String> engineId;
    @FXML
    private TableColumn<HelicopterDTO, String> engineName;
    @FXML
    private TableColumn<HelicopterDTO, String> engineYear;
    @FXML
    private TableColumn<HelicopterDTO, String> engineMaId;
    @FXML
    private TableColumn<HelicopterDTO, String> engineMaName;
    @FXML
    private TableColumn<HelicopterDTO, String> engineMaCount;

    @FXML
    void initialize() {

        retback.setOnAction(event -> {
            application.setStage(8);
        });

        updateBut.setOnAction(event -> {
            List<Helicopter> helicopters = helicopterService.getHelicopters();
            printData(helicopters);
        });
    }

    private void printData(List<Helicopter> helicopters) {
        List<HelicopterDTO> data = helicopters.stream().map(d -> {
            return new HelicopterDTO(d.getId(), d.getName(), d.getYear(), d.getManufacturer().getId(),
                    d.getManufacturer().getName(), d.getManufacturer().getCountry().getName(),
                    d.getEngineType().getId(), d.getEngineType().getName(), d.getEngineType().getYear(),
                    d.getEngineType().getManufacturer().getId(), d.getEngineType().getManufacturer().getName(),
                    d.getEngineType().getManufacturer().getCountry().getName(), d.getNumberSeats(), d.getFuelConsumption());
        }).collect(Collectors.toList());
        idColumn.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("name"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, Integer>("year"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, Integer>("numberSeats"));
        fuelColumn.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, Double>("fuelConsumption"));
        manufacturerId.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("manufacturerId"));
        manufacturerName.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("manufacturerName"));
        manufacturerCount.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("manufacturerCountry"));
        engineId.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("idEngineType"));
        engineYear.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("yearEngineType"));
        engineName.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("nameEngineType"));
        engineMaId.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("manufacturerIdEngineType"));
        engineMaName.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("manufacturerNameEngineType"));
        engineMaCount.setCellValueFactory(new PropertyValueFactory<HelicopterDTO, String>("manufacturerCountryEngineType"));
        helicopterTableView.setItems(FXCollections.observableArrayList(data));

    }

}
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class HelSearchController {

    @Autowired
    private HelicopterService helicopterService;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField key;
    @FXML
    private Button entKey;

    @FXML
    private TextField diap1;
    @FXML
    private TextField diap2;
    @FXML
    private Button entDiap;

    @FXML
    private RadioButton var1;
    @FXML
    private RadioButton var2;
    @FXML
    private RadioButton var3;
    @FXML
    private RadioButton var4;
    @FXML
    private RadioButton var5;
    @FXML
    private RadioButton var6;

    @FXML
    private Button retback;

    @FXML
    ToggleGroup varr;

    @FXML
    ToggleGroup vaar;

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
            errorMessage.setText("");
            application.setStage(8);
        });

        entKey.setOnAction(event -> {
            if (varr.getSelectedToggle() == null) {
                errorMessage.setText("Выберите параметр поиска");
            } else if (key.getText().equals("")) {
                errorMessage.setText("Введите ключ поиска");
            } else {
                errorMessage.setText("");
                List<Helicopter> helicopters = null;
                if (varr.getSelectedToggle().equals(var1)) {
                    helicopters = helicopterService.searchByNameHelicopters(key.getText());
                } else if (varr.getSelectedToggle().equals(var2)) {
                    helicopters = helicopterService.searchByManufHelicopters(key.getText());
                } else if (varr.getSelectedToggle().equals(var3)) {
                    helicopters = helicopterService.searchByEngineHelicopters(key.getText());
                }
                printData(helicopters);
            }
        });

        entDiap.setOnAction(event -> {
            if (vaar.getSelectedToggle() == null) {
                errorMessage.setText("Выберите параметр поиска");
            } else if (diap1.getText().equals("") || diap2.getText().equals("")) {
                errorMessage.setText("Введите диапазон поиска");
            } else {
                try {
                    errorMessage.setText("");
                    Double d1 = Double.valueOf(diap1.getText());
                    Double d2 = Double.valueOf(diap2.getText());
                    List<Helicopter> helicopters = null;
                    if (vaar.getSelectedToggle().equals(var4)) {
                        helicopters = helicopterService.searchByYearHelicopters(d1, d2);
                    } else if (vaar.getSelectedToggle().equals(var5)) {
                        helicopters = helicopterService.searchByFuelConsumptionHelicopters(d1, d2);
                    } else if (vaar.getSelectedToggle().equals(var6)) {
                        helicopters = helicopterService.searchByNumberSeatsHelicopters(d1, d2);
                    }
                    printData(helicopters);
                } catch (NumberFormatException e) {
                    errorMessage.setText("Введите числовое значение");
                }
            }
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
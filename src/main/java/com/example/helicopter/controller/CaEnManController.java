package com.example.helicopter.controller;

import com.example.helicopter.Application;
import com.example.helicopter.entity.Country;
import com.example.helicopter.entity.EngineManufacturer;
import com.example.helicopter.service.CountryService;
import com.example.helicopter.service.EngineManufacturerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CaEnManController {

    @FXML
    private Label errorMessageCatEnMan;
    @FXML
    private TextField nameEngineMan;
    @FXML
    private Button enterCatEnManBut;
    @FXML
    private ChoiceBox<String> countryList;
    @FXML
    private Button updateTable;

    @FXML
    private TableView<EngineManufacturer> countriesTable;
    @FXML
    private TableColumn<EngineManufacturer,Long> idColumn;
    @FXML
    private TableColumn<EngineManufacturer, String> nameColumn;
    @FXML
    private TableColumn<EngineManufacturer, Country> countryColumn;

    @FXML
    private Button retback;
    
    @Autowired
    private Application application;

    @Autowired
    private EngineManufacturerService engineManufacturerService;
    @Autowired
    private CountryService countryService;

    private ObservableList<String> items;

    @FXML
    void initialize() {
        items = FXCollections.observableArrayList(new ArrayList<>());
        countryList.setItems(items);

        retback.setOnAction(event -> {
            errorMessageCatEnMan.setText("");
            application.setStage(10);
        });

        updateTable.setOnAction(event -> {
            errorMessageCatEnMan.setText("");
            printData(engineManufacturerService.getEngineManufacturies());
            List<Country> countries = countryService.getCountries();
            List<String> countriesNames = countries.stream().map(Country::getName).collect(Collectors.toList());
            items = FXCollections.observableArrayList(countriesNames);
            countryList.setItems(items);
        });

        enterCatEnManBut.setOnAction(event -> {
            if (nameEngineMan.getText().equals("")
                    || countryList.getValue().equals("")) {
                errorMessageCatEnMan.setText("Поля не должны быть пустыми");
            } else {
                errorMessageCatEnMan.setText("");
                EngineManufacturer engineManufacturer
                        = engineManufacturerService.findEngineManufacturerByName(nameEngineMan.getText());
                if (engineManufacturer == null) {
                    EngineManufacturer engineManufacturerNew = new EngineManufacturer();
                    engineManufacturerNew.setName(nameEngineMan.getText());
                    Country country
                            = countryService.findCountryByName(countryList.getValue());
                    if (country != null) {
                        engineManufacturerNew.setCountry(country);
                        engineManufacturerService.addEngineManufacturer(engineManufacturerNew);
                        printData(engineManufacturerService.getEngineManufacturies());
                        errorMessageCatEnMan.setText("Запись успешно добавлена");
                    } else {
                        errorMessageCatEnMan.setText("Указанная страна не найдена");
                    }
                } else {
                    errorMessageCatEnMan.setText("Запись с таким значением уже существует");
                }
            }
        });

    }

    private void printData(List<EngineManufacturer> engineManufacturers) {
        idColumn.setCellValueFactory(new PropertyValueFactory<EngineManufacturer, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<EngineManufacturer, String>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<EngineManufacturer, Country>("country"));
        countriesTable.setItems(FXCollections.observableArrayList(engineManufacturers));
    }

}
package com.example.helicopter.controller;

import com.example.helicopter.Application;
import com.example.helicopter.entity.Country;
import com.example.helicopter.entity.HelicopterManufacturer;
import com.example.helicopter.service.CountryService;
import com.example.helicopter.service.HelicopterManufacturerService;
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

public class CatHelManController {

    @FXML
    private Label errorMessageCatHelMan;
    @FXML
    private TextField nameHelManu;
    @FXML
    private ChoiceBox<String> countryList;
    @FXML
    private Button enterCatHelManBut;
    @FXML
    private Button updateTable;

    @FXML
    private TableView<HelicopterManufacturer> countriesTable;
    @FXML
    private TableColumn<HelicopterManufacturer, Long> idColumn;
    @FXML
    private TableColumn<HelicopterManufacturer, String> nameColumn;
    @FXML
    private TableColumn<HelicopterManufacturer, Country> countryColumn;

    @FXML
    private Button retback;

    @Autowired
    private Application application;

    @Autowired
    private HelicopterManufacturerService helicopterManufacturerService;
    @Autowired
    private CountryService countryService;

    private ObservableList<String> items;

    @FXML
    void initialize() {
        items = FXCollections.observableArrayList(new ArrayList<>());
        countryList.setItems(items);

        retback.setOnAction(event -> {
            errorMessageCatHelMan.setText("");
            application.setStage(10);
        });

        updateTable.setOnAction(event -> {
            errorMessageCatHelMan.setText("");
            printData(helicopterManufacturerService.getHelicopterManufacturies());
            List<Country> countries = countryService.getCountries();
            List<String> countriesNames = countries.stream().map(Country::getName).collect(Collectors.toList());
            items = FXCollections.observableArrayList(countriesNames);
            countryList.setItems(items);
        });

        enterCatHelManBut.setOnAction(event -> {
            if (nameHelManu.getText().equals("")
                    || countryList.getValue().equals("")) {
                errorMessageCatHelMan.setText("Поля не должны быть пустыми");
            } else {
                errorMessageCatHelMan.setText("");
                HelicopterManufacturer helicopterManufacturerLast
                        = helicopterManufacturerService.findHelicopterManufacturerByName(nameHelManu.getText());
                if (helicopterManufacturerLast == null) {
                    HelicopterManufacturer helicopterManufacturerNew = new HelicopterManufacturer();
                    helicopterManufacturerNew.setName(nameHelManu.getText());
                    Country country
                            = countryService.findCountryByName(countryList.getValue());
                    if (country != null) {
                        helicopterManufacturerNew.setCountry(country);
                        helicopterManufacturerService.addHelicopterManufacturer(helicopterManufacturerNew);
                        printData(helicopterManufacturerService.getHelicopterManufacturies());
                        errorMessageCatHelMan.setText("Запись успешно добавлена");
                    } else {
                        errorMessageCatHelMan.setText("Указанная страна не найдена");
                    }
                } else {
                    errorMessageCatHelMan.setText("Запись с таким значением уже существует");
                }
            }
        });

    }

    private void printData(List<HelicopterManufacturer> helicopterManufacturers) {
        idColumn.setCellValueFactory(new PropertyValueFactory<HelicopterManufacturer, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<HelicopterManufacturer, String>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<HelicopterManufacturer, Country>("country"));
        countriesTable.setItems(FXCollections.observableArrayList(helicopterManufacturers));
    }

}
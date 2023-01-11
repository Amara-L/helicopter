package com.example.helicopter.controller;

import com.example.helicopter.Application;
import com.example.helicopter.entity.Country;
import com.example.helicopter.service.CountryService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CatCountryController {

    @FXML
    private Label errorMessageCatCou;
    @FXML
    private TextField catCouWin;
    @FXML
    private Button enterCatCouBut;
    @FXML
    private Button updateCountrTable;

    @FXML
    private TableView<Country> countriesTable;
    @FXML
    private TableColumn<Country, Long> idColumn;
    @FXML
    private TableColumn<Country, String> nameColumn;

    @FXML
    private Button retback;

    @Autowired
    private Application application;

    @Autowired
    private CountryService countryService;

    @FXML
    void initialize() {

        retback.setOnAction(event -> {
            errorMessageCatCou.setText("");
            application.setStage(10);
        });

        updateCountrTable.setOnAction(event -> {
            errorMessageCatCou.setText("");
            printData(countryService.getCountries());
        });

        enterCatCouBut.setOnAction(event -> {
            if (catCouWin.getText().equals("")) {
                errorMessageCatCou.setText("Поле не должно быть пустым");
            } else {
                errorMessageCatCou.setText("");
                Country countryLast = countryService.findCountryByName(catCouWin.getText());
                if (countryLast == null) {
                    Country countryNew = new Country();
                    countryNew.setName(catCouWin.getText());
                    countryService.addCountry(countryNew);
                    printData(countryService.getCountries());
                    errorMessageCatCou.setText("Запись успешно добавлена");
                } else {
                    errorMessageCatCou.setText("Запись с таким значением уже существует");
                }
            }
        });

    }

    private void printData(List<Country> countries) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Country, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));
        countriesTable.setItems(FXCollections.observableArrayList(countries));
    }

}
package com.example.helicopter.controller;

import com.example.helicopter.Application;
import com.example.helicopter.entity.EngineType;
import com.example.helicopter.entity.Helicopter;
import com.example.helicopter.entity.HelicopterManufacturer;
import com.example.helicopter.service.EngineTypeService;
import com.example.helicopter.service.HelicopterManufacturerService;
import com.example.helicopter.service.HelicopterService;
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

public class HelicopController {

    @FXML
    private Label errorMessageCatHel;
    @FXML
    private TextField nameHeli;
    @FXML
    private TextField seatsHeli;
    @FXML
    private TextField yearHeli;
    @FXML
    private TextField fuelHeli;
    @FXML
    private Button enterCatHelBut;

    @FXML
    private Button updateTable;

    @FXML
    private ChoiceBox<String> engineTypeList;
    @FXML
    private ChoiceBox<String> manufList;

    @FXML
    private Button retback;

    @FXML
    private TableView helicopterTableView;
    @FXML
    private TableColumn<Helicopter, Long> idColumn;
    @FXML
    private TableColumn<Helicopter, String> nameColumn;
    @FXML
    private TableColumn<Helicopter, Integer> yearColumn;
    @FXML
    private TableColumn<Helicopter, Integer> seatsColumn;
    @FXML
    private TableColumn<Helicopter, Double> fuelColumn;
    @FXML
    private TableColumn<Helicopter, EngineType> engineColumn;
    @FXML
    private TableColumn<Helicopter, HelicopterManufacturer> manufacturerColumn;

    @Autowired
    private Application application;

    @Autowired
    private HelicopterService helicopterService;
    @Autowired
    private HelicopterManufacturerService helicopterManufacturerService;
    @Autowired
    private EngineTypeService engineTypeService;

    private ObservableList<String> itemsEngineTypeList;

    private ObservableList<String> itemsManufList;

    @FXML
    void initialize() {
        itemsEngineTypeList = FXCollections.observableArrayList(new ArrayList<>());
        itemsManufList = FXCollections.observableArrayList(new ArrayList<>());
        engineTypeList.setItems(itemsEngineTypeList);
        manufList.setItems(itemsManufList);

        retback.setOnAction(event -> {
            errorMessageCatHel.setText("");
            application.setStage(10);
        });

        updateTable.setOnAction(event -> {
            errorMessageCatHel.setText("");
            printData(helicopterService.getHelicopters());
            List<HelicopterManufacturer> engineManufacturers = helicopterManufacturerService.getHelicopterManufacturies();
            List<String> manufacturers = engineManufacturers.stream().map(HelicopterManufacturer::getName).collect(Collectors.toList());
            itemsManufList = FXCollections.observableArrayList(manufacturers);
            List<EngineType> engineTypes = engineTypeService.getEngineTypes();
            List<String> engineTypesNames = engineTypes.stream().map(EngineType::getName).collect(Collectors.toList());
            itemsEngineTypeList = FXCollections.observableArrayList(engineTypesNames);
            engineTypeList.setItems(itemsEngineTypeList);
            manufList.setItems(itemsManufList);
        });

        enterCatHelBut.setOnAction(event -> {
            if (nameHeli.getText().equals("") || seatsHeli.getText().equals("")
                    || yearHeli.getText().equals("") || fuelHeli.getText().equals("")
                    || engineTypeList.getValue().equals("")
                    || manufList.getValue().equals("")) {
                errorMessageCatHel.setText("Поля не должны быть пустыми");
            } else {
                try {
                    Integer year = Integer.valueOf(yearHeli.getText());
                    Integer seats = Integer.valueOf(seatsHeli.getText());
                    Double fuel = Double.valueOf(fuelHeli.getText());
                    errorMessageCatHel.setText("");
                    Helicopter helicopterLast = helicopterService.findHelicopterByName(nameHeli.getText());
                    if (helicopterLast == null) {
                        Helicopter helicopterNew = new Helicopter();
                        helicopterNew.setName(nameHeli.getText());
                        helicopterNew.setYear(year);
                        helicopterNew.setNumberSeats(seats);
                        helicopterNew.setFuelConsumption(fuel);
                        HelicopterManufacturer manufacturer
                                = helicopterManufacturerService.findHelicopterManufacturerByName(manufList.getValue());
                        EngineType engineType
                                = engineTypeService.findEngineTypeByName(engineTypeList.getValue());
                        if (manufacturer != null) {
                            if (engineType != null) {
                                helicopterNew.setManufacturer(manufacturer);
                                helicopterNew.setEngineType(engineType);
                                helicopterService.addHelicopter(helicopterNew);
                                printData(helicopterService.getHelicopters());
                                errorMessageCatHel.setText("Запись успешно добавлена");
                            } else {
                                errorMessageCatHel.setText("Тип двигателя не найден");
                            }
                        } else {
                            errorMessageCatHel.setText("Производитель не найден");
                        }
                    } else {
                        errorMessageCatHel.setText("Запись с таким значением уже существует");
                    }
                } catch (NumberFormatException e) {
                    errorMessageCatHel.setText("Введите числовое значение");
                }
            }
        });

    }

    private void printData(List<Helicopter> helicopters) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Helicopter, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Helicopter, String>("name"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Helicopter, Integer>("year"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<Helicopter, Integer>("numberSeats"));
        fuelColumn.setCellValueFactory(new PropertyValueFactory<Helicopter, Double>("fuelConsumption"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<Helicopter, HelicopterManufacturer>("manufacturer"));
        engineColumn.setCellValueFactory(new PropertyValueFactory<Helicopter, EngineType>("engineType"));
        helicopterTableView.setItems(FXCollections.observableArrayList(helicopters));
    }

}
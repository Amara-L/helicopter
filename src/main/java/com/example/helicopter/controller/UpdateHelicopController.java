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

public class UpdateHelicopController {

    @FXML
    private TextField idField;
    @FXML
    private Button search;

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
    private TableView<Helicopter> helicopterTableView;
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
            setDisableFlag(true);
            application.setStage(8);
        });

        updateTable.setOnAction(event -> {
            errorMessageCatHel.setText("");
            setDisableFlag(true);
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

        search.setOnAction(event -> {
            if (idField.getText().equals("")) {
                errorMessageCatHel.setText("Введите идентификатор записи");
                setDisableFlag(true);
            } else {
                try {
                    errorMessageCatHel.setText("");
                    Long id = Long.valueOf(idField.getText());
                    Helicopter helicopterLast = helicopterService.getHelicopterById(id);
                    if (helicopterLast != null) {
                        setDisableFlag(false);
                        nameHeli.setText(helicopterLast.getName());
                        seatsHeli.setText(helicopterLast.getNumberSeats().toString());
                        yearHeli.setText(helicopterLast.getYear().toString());
                        fuelHeli.setText(helicopterLast.getFuelConsumption().toString());
                        manufList.setValue(helicopterLast.getManufacturer().getName());
                        engineTypeList.setValue(helicopterLast.getEngineType().getName());
                    } else {
                        errorMessageCatHel.setText("Запись с указанным идентификатором не найдена");
                        setDisableFlag(true);
                    }
                } catch (NumberFormatException e) {
                    errorMessageCatHel.setText("Введите числовое значение");
                    setDisableFlag(true);
                }
            }
        });

        enterCatHelBut.setOnAction(event -> {
            if (idField.getText().equals("")
                    || nameHeli.getText().equals("") || seatsHeli.getText().equals("")
                    || yearHeli.getText().equals("") || fuelHeli.getText().equals("")
                    || engineTypeList.getValue().equals("")
                    || manufList.getValue().equals("")) {
                errorMessageCatHel.setText("Поля не должны быть пустыми");
            } else {
                try {
                    Long id = Long.valueOf(idField.getText());
                    Integer year = Integer.valueOf(yearHeli.getText());
                    Integer seats = Integer.valueOf(seatsHeli.getText());
                    Double fuel = Double.valueOf(fuelHeli.getText());
                    errorMessageCatHel.setText("");
                    Helicopter helicopterOther = helicopterService.findHelicopterByName(nameHeli.getText());
                    Helicopter helicopterLast = helicopterService.getHelicopterById(id);
                    if (helicopterOther == null || helicopterLast.getId().equals(helicopterOther.getId())) {
                        helicopterLast.setName(nameHeli.getText());
                        helicopterLast.setYear(year);
                        helicopterLast.setNumberSeats(seats);
                        helicopterLast.setFuelConsumption(fuel);
                        HelicopterManufacturer manufacturer
                                = helicopterManufacturerService.findHelicopterManufacturerByName(manufList.getValue());
                        EngineType engineType
                                = engineTypeService.findEngineTypeByName(engineTypeList.getValue());
                        if (manufacturer != null) {
                            if (engineType != null) {
                                helicopterLast.setManufacturer(manufacturer);
                                helicopterLast.setEngineType(engineType);
                                helicopterService.addHelicopter(helicopterLast);
                                printData(helicopterService.getHelicopters());
                                errorMessageCatHel.setText("Запись успешно обновлена");
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

    private void setDisableFlag(Boolean flag) {
        nameHeli.setDisable(flag);
        seatsHeli.setDisable(flag);
        yearHeli.setDisable(flag);
        fuelHeli.setDisable(flag);
        enterCatHelBut.setDisable(flag);
        manufList.setDisable(flag);
        engineTypeList.setDisable(flag);
    }

}

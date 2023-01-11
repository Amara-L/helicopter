package com.example.helicopter.controller;

import com.example.helicopter.Application;
import com.example.helicopter.entity.EngineManufacturer;
import com.example.helicopter.entity.EngineType;
import com.example.helicopter.service.EngineManufacturerService;
import com.example.helicopter.service.EngineTypeService;
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

public class CatEnTypeController {

    @FXML
    private Label errorMessageCatEnType;
    @FXML
    private TextField yearField;
    @FXML
    private TextField nameField;
    @FXML
    private ChoiceBox<String> manufactorerList;
    @FXML
    private Button enterCatEnTypeBut;

    @FXML
    private TableView<EngineType> countriesTable;
    @FXML
    private TableColumn<EngineType,Long> idColumn;
    @FXML
    private TableColumn<EngineType, String> nameColumn;
    @FXML
    private TableColumn<EngineType, Integer> yearColumn;
    @FXML
    private TableColumn<EngineType, EngineManufacturer> manufacturerColumn;

    @FXML
    private Button updateTable;

    @FXML
    private Button retback;

    @Autowired
    private Application application;

    @Autowired
    private EngineTypeService engineTypeService;

    @Autowired
    private EngineManufacturerService engineManufacturerService;

    private ObservableList<String> items;

    @FXML
    void initialize() {
        items = FXCollections.observableArrayList(new ArrayList<>());
        manufactorerList.setItems(items);

        retback.setOnAction(event -> {
            errorMessageCatEnType.setText("");
            application.setStage(10);
        });

        updateTable.setOnAction(event -> {
            errorMessageCatEnType.setText("");
            printData(engineTypeService.getEngineTypes());
            List<EngineManufacturer> engineManufacturers = engineManufacturerService.getEngineManufacturies();
            List<String> manufacturers = engineManufacturers.stream().map(EngineManufacturer::getName).collect(Collectors.toList());
            items = FXCollections.observableArrayList(manufacturers);
            manufactorerList.setItems(items);
        });

        enterCatEnTypeBut.setOnAction(event -> {
            if (yearField.getText().equals("") || nameField.getText().equals("")
                    || manufactorerList.getValue().equals("")) {
                errorMessageCatEnType.setText("Поля не должны быть пустыми");
            } else {
                try {
                    Integer year = Integer.valueOf(yearField.getText());
                    errorMessageCatEnType.setText("");
                    EngineType engineTypeLast =  engineTypeService.findEngineTypeByName(nameField.getText());
                if (engineTypeLast == null) {
                    EngineType engineTypeNew = new EngineType();
                    engineTypeNew.setYear(year);
                    engineTypeNew.setName(nameField.getText());
                    EngineManufacturer engineManufacturer
                            = engineManufacturerService.findEngineManufacturerByName(manufactorerList.getValue());
                    if (engineManufacturer != null) {
                        engineTypeNew.setManufacturer(engineManufacturer);
                        engineTypeService.addEngineType(engineTypeNew);
                        printData(engineTypeService.getEngineTypes());
                        errorMessageCatEnType.setText("Запись успешно добавлена");
                    } else {
                        errorMessageCatEnType.setText("Производитель не найден");
                    }
                } else {
                    errorMessageCatEnType.setText("Запись с таким значением уже существует");
                }
                } catch (NumberFormatException e) {
                    errorMessageCatEnType.setText("Введите числовое значение");
                }
            }
        });

    }

    private void printData(List<EngineType> engineTypes) {
        idColumn.setCellValueFactory(new PropertyValueFactory<EngineType, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<EngineType, String>("name"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<EngineType, Integer>("year"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<EngineType, EngineManufacturer>("manufacturer"));
        countriesTable.setItems(FXCollections.observableArrayList(engineTypes));
    }

}
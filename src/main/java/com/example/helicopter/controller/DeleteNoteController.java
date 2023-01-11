package com.example.helicopter.controller;

import com.example.helicopter.Application;
import com.example.helicopter.entity.Country;
import com.example.helicopter.entity.EngineManufacturer;
import com.example.helicopter.entity.EngineType;
import com.example.helicopter.entity.Helicopter;
import com.example.helicopter.entity.HelicopterManufacturer;
import com.example.helicopter.service.CountryService;
import com.example.helicopter.service.EngineManufacturerService;
import com.example.helicopter.service.EngineTypeService;
import com.example.helicopter.service.HelicopterManufacturerService;
import com.example.helicopter.service.HelicopterService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;

public class DeleteNoteController {

    @FXML
    private TextField idField;
    @FXML
    private Button delBut;

    @FXML
    private Label errorMessageCatHel;
    @FXML
    private Button retback;

    @FXML
    private TextArea dataField;
    @FXML
    private Button okBut;

    @FXML
    private ChoiceBox<String> tablesList;

    @Autowired
    private Application application;

    @Autowired
    private CountryService countryService;
    @Autowired
    private EngineTypeService engineTypeService;
    @Autowired
    private EngineManufacturerService engineManufacturerService;
    @Autowired
    private HelicopterManufacturerService helicopterManufacturerService;
    @Autowired
    private HelicopterService helicopterService;

    private ObservableList<String> items;

    @FXML
    void initialize() {
        ArrayList<String> listTables = new ArrayList<>();
        listTables.add("Страна");
        listTables.add("Тип двигателя");
        listTables.add("Производитель двигателей");
        listTables.add("Производитель вертолетов");
        listTables.add("Вертолет");
        items = FXCollections.observableArrayList(listTables);
        tablesList.setItems(items);

        retback.setOnAction(event -> {
            errorMessageCatHel.setText("");
            dataField.setText("");
            okBut.setDisable(true);
            application.setStage(8);
        });

        delBut.setOnAction(event -> {
            if (idField.getText().equals("") || tablesList.getValue().equals("")) {
                errorMessageCatHel.setText("Введите данные");
                dataField.setText("");
                okBut.setDisable(true);
            } else {
                try {
                    errorMessageCatHel.setText("");
                    String table = tablesList.getValue();
                    Long id = Long.valueOf(idField.getText());
                    switch (table) {
                        case "Страна": {
                            Country country = countryService.getCountryById(id);
                            if (country == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                StringBuilder stringBuilder = new StringBuilder("Запись:\n")
                                        .append("Идентификатор: ")
                                        .append(country.getId())
                                        .append("\n")
                                        .append("Наименование   ")
                                        .append(country.getName());
                                dataField.setText(stringBuilder.toString());
                                okBut.setDisable(false);
                            }
                            break;
                        }
                        case "Тип двигателя": {
                            EngineType engineType = engineTypeService.getEngineTypeById(id);
                            if (engineType == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                StringBuilder stringBuilder = new StringBuilder("Запись:\n")
                                        .append("Идентификатор:         ")
                                        .append(engineType.getId())
                                        .append("\n")
                                        .append("Наименование:          ")
                                        .append(engineType.getName())
                                        .append("\n")
                                        .append("Год:                   ")
                                        .append(engineType.getYear())
                                        .append("\n")
                                        .append("Производитель:         ")
                                        .append("\n")
                                        .append("|  Идентификатор:      ")
                                        .append(engineType.getManufacturer().getId())
                                        .append("\n")
                                        .append("|  Наименование:       ")
                                        .append(engineType.getManufacturer().getName())
                                        .append("\n")
                                        .append("|  Страна:             ")
                                        .append("\n")
                                        .append("   |  Идентификатор:   ")
                                        .append(engineType.getManufacturer().getCountry().getId())
                                        .append("\n")
                                        .append("   |  Наименование:    ")
                                        .append(engineType.getManufacturer().getCountry().getName());
                                dataField.setText(stringBuilder.toString());
                                okBut.setDisable(false);
                            }
                            break;
                        }
                        case "Производитель двигателей": {
                            EngineManufacturer engineManufacturer = engineManufacturerService.getEngineManufacturerById(id);
                            if (engineManufacturer == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                StringBuilder stringBuilder = new StringBuilder("Запись:\n")
                                        .append("Идентификатор:    ")
                                        .append(engineManufacturer.getId())
                                        .append("\n")
                                        .append("Наименование      ")
                                        .append(engineManufacturer.getName())
                                        .append("\n")
                                        .append("Страна            ")
                                        .append("\n")
                                        .append(" | Идентификатор: ")
                                        .append(engineManufacturer.getCountry().getId())
                                        .append("\n")
                                        .append(" | Наименование   ")
                                        .append(engineManufacturer.getCountry().getName());
                                dataField.setText(stringBuilder.toString());
                                okBut.setDisable(false);
                            }
                            break;
                        }
                        case "Производитель вертолетов": {
                            HelicopterManufacturer helicopterManufacturer
                                    = helicopterManufacturerService.getHelicopterManufacturerById(id);
                            if (helicopterManufacturer == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                StringBuilder stringBuilder = new StringBuilder("Запись:\n")
                                        .append("Идентификатор:    ")
                                        .append(helicopterManufacturer.getId())
                                        .append("\n")
                                        .append("Наименование      ")
                                        .append(helicopterManufacturer.getName())
                                        .append("\n")
                                        .append("Страна            ")
                                        .append("\n")
                                        .append(" | Идентификатор: ")
                                        .append(helicopterManufacturer.getCountry().getId())
                                        .append("\n")
                                        .append(" | Наименование   ")
                                        .append(helicopterManufacturer.getCountry().getName());
                                dataField.setText(stringBuilder.toString());
                                okBut.setDisable(false);
                            }
                            break;
                        }
                        case "Вертолет": {
                            Helicopter helicopter = helicopterService.getHelicopterById(id);
                            if (helicopter == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                StringBuilder stringBuilder = new StringBuilder("Запись:\n")
                                        .append("Идентификатор:         ")
                                        .append(helicopter.getId())
                                        .append("\n")
                                        .append("Наименование:          ")
                                        .append(helicopter.getName())
                                        .append("\n")
                                        .append("Год выпуска:           ")
                                        .append(helicopter.getYear())
                                        .append("\n")
                                        .append("Количество мест:       ")
                                        .append(helicopter.getNumberSeats())
                                        .append("\n")
                                        .append("Расход топлива:        ")
                                        .append(helicopter.getFuelConsumption())
                                        .append(" л/100км\n")
                                        .append("Двигатель:             ")
                                        .append("\n")
                                        .append(" | Идентификатор:      ")
                                        .append(helicopter.getEngineType().getId())
                                        .append("\n")
                                        .append(" | Наименование:       ")
                                        .append(helicopter.getEngineType().getName())
                                        .append("\n")
                                        .append(" | Год выпуска:        ")
                                        .append(helicopter.getEngineType().getYear())
                                        .append("\n")
                                        .append(" | Производитель:      ")
                                        .append("\n")
                                        .append("    | Идентификатор:   ")
                                        .append(helicopter.getEngineType().getManufacturer().getId())
                                        .append("\n")
                                        .append("    | Наименование:    ")
                                        .append(helicopter.getEngineType().getManufacturer().getName())
                                        .append("\n")
                                        .append("    | Страна:          ")
                                        .append(helicopter.getEngineType().getManufacturer().getCountry().getName())
                                        .append("\n")
                                        .append(" | Производитель:      ")
                                        .append("\n")
                                        .append("    | Идентификатор:   ")
                                        .append(helicopter.getManufacturer().getId())
                                        .append("\n")
                                        .append("    | Наименование:    ")
                                        .append(helicopter.getManufacturer().getName())
                                        .append("\n")
                                        .append("    | Страна:          ")
                                        .append(helicopter.getManufacturer().getCountry().getName());
                                dataField.setText(stringBuilder.toString());
                                okBut.setDisable(false);
                            }
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    errorMessageCatHel.setText("Введите числовое значение");
                    dataField.setText("");
                    okBut.setDisable(true);
                }
            }
        });

        okBut.setOnAction(event -> {
            if (idField.getText().equals("") || tablesList.getValue().equals("")) {
                errorMessageCatHel.setText("Введите данные");
                dataField.setText("");
                okBut.setDisable(true);
            } else {
                try {
                    errorMessageCatHel.setText("");
                    String table = tablesList.getValue();
                    Long id = Long.valueOf(idField.getText());
                    switch (table) {
                        case "Страна": {
                            Country country = countryService.getCountryById(id);
                            if (country == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                countryService.deleteCountry(country);
                                errorMessageCatHel.setText("Запись успешно удалена");
                                dataField.setText("");
                                okBut.setDisable(true);
                            }
                            break;
                        }
                        case "Тип двигателя": {
                            EngineType engineType = engineTypeService.getEngineTypeById(id);
                            if (engineType == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                engineTypeService.deleteEngineType(engineType);
                                errorMessageCatHel.setText("Запись успешно удалена");
                                dataField.setText("");
                                okBut.setDisable(true);
                            }
                            break;
                        }
                        case "Производитель двигателей": {
                            EngineManufacturer engineManufacturer = engineManufacturerService.getEngineManufacturerById(id);
                            if (engineManufacturer == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                engineManufacturerService.deleteEngineManufacturer(engineManufacturer);
                                errorMessageCatHel.setText("Запись успешно удалена");
                                dataField.setText("");
                                okBut.setDisable(true);
                            }
                            break;
                        }
                        case "Производитель вертолетов": {
                            HelicopterManufacturer helicopterManufacturer
                                    = helicopterManufacturerService.getHelicopterManufacturerById(id);
                            if (helicopterManufacturer == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                helicopterManufacturerService.deleteHelicopterManufacturer(helicopterManufacturer);
                                errorMessageCatHel.setText("Запись успешно удалена");
                                dataField.setText("");
                                okBut.setDisable(true);
                            }
                            break;
                        }
                        case "Вертолет": {
                            Helicopter helicopter = helicopterService.getHelicopterById(id);
                            if (helicopter == null) {
                                dataField.setText("");
                                okBut.setDisable(true);
                                errorMessageCatHel.setText("Запись по указанному идентификатору не найдена");
                            } else {
                                helicopterService.deleteHelicopterById(id);
                                errorMessageCatHel.setText("Запись успешно удалена");
                                dataField.setText("");
                                okBut.setDisable(true);
                            }
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    errorMessageCatHel.setText("Введите числовое значение");
                    dataField.setText("");
                    okBut.setDisable(true);
                } catch (DataIntegrityViolationException e) {
                    errorMessageCatHel.setText("Удаление невозможно: обнаружены связанные записи");
                    dataField.setText("");
                    okBut.setDisable(true);
                }
            }
        });

    }

}

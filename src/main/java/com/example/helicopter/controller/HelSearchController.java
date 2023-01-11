package com.example.helicopter.controller;

import com.example.helicopter.Application;
import com.example.helicopter.HelicopterService;
import com.example.helicopter.entity.Helicopter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    private TextArea searchData;

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
                    helicopters = helicopterService.searchByNameHelicopters(key.getText());
                } else if (varr.getSelectedToggle().equals(var3)) {
                    helicopters = helicopterService.searchByNameHelicopters(key.getText());
                }
                searchData.setText(helicopterService.createText(helicopters));
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
                    searchData.setText(helicopterService.createText(helicopters));
                } catch (NumberFormatException e) {
                    errorMessage.setText("Введите числовое значение");
                }
            }
        });

    }

}
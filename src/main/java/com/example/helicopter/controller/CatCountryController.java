package com.example.helicopter.controller;

import com.example.helicopter.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class CatCountryController {

    @FXML
    private Label errorMessageCatCou;
    @FXML
    private TextField catCouWin;
    @FXML
    private Button enterCatCouBut;

    @FXML
    private Button retback;

    @Autowired
    private Application application;

    @FXML
    void initialize() {

        retback.setOnAction(event -> {
            application.setStage(8);
        });

    }

}
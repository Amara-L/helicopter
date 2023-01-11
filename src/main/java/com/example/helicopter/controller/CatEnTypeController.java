package com.example.helicopter.controller;

import com.example.helicopter.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class CatEnTypeController {

    @FXML
    private Label errorMessageCatEnType;
    @FXML
    private TextField catEnTypeWin;
    @FXML
    private Button enterCatEnTypeBut;

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
package com.example.helicopter.controller;

import com.example.helicopter.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class HelicopController {

    @FXML
    private Label errorMessageCatHel;
    @FXML
    private TextField catHelWin;
    @FXML
    private Button enterCatHelBut;

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
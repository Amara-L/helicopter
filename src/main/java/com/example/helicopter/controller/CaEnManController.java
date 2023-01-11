package com.example.helicopter.controller;

import com.example.helicopter.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class CaEnManController {

    @FXML
    private Label errorMessageCatEnMan;
    @FXML
    private TextField catEnManWin;
    @FXML
    private Button enterCatEnManBut;

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
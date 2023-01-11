package com.example.helicopter.controller;

import com.example.helicopter.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;

public class MainController {

    @FXML
    private Button helicopterSearchBut;
    @FXML
    private Button helicopterListBut;
    @FXML
    private Button editNoteBut;
    @FXML
    private Button addNoteBut;
    @FXML
    private Button deleteNotesBut;

    @Autowired
    private Application application;

    @FXML
    void initialize() {

        helicopterSearchBut.setOnAction(event -> {
            application.setStage(6);
        });

        helicopterListBut.setOnAction(event -> {
            application.setStage(7);
        });

        editNoteBut.setOnAction(event -> {
            application.setStage(9);
        });

        addNoteBut.setOnAction(event -> {
            application.setStage(10);
        });

        deleteNotesBut.setOnAction(event -> {
            application.setStage(11);
        });

    }

}
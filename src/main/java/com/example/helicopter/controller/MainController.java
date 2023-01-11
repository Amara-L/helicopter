package com.example.helicopter.controller;

import com.example.helicopter.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;

public class MainController {

    @FXML
    private Button addManEnBut;
    @FXML
    private Button addManHeliBut;
    @FXML
    private Button addEnTypeBut;
    @FXML
    private Button addHelicopterBut;
    @FXML
    private Button addCountBut;
    @FXML
    private Button helicopterSearchBut;
    @FXML
    private Button helicopterListBut;

    @Autowired
    private Application application;

    @FXML
    void initialize() {

        addManEnBut.setOnAction(event -> {
            application.setStage(1);
        });


        addManHeliBut.setOnAction(event -> {
            application.setStage(2);
        });


        addEnTypeBut.setOnAction(event -> {
            application.setStage(3);
        });

        addHelicopterBut.setOnAction(event -> {
            application.setStage(4);
        });


        addCountBut.setOnAction(event -> {
            application.setStage(5);
        });


        helicopterSearchBut.setOnAction(event -> {
            application.setStage(6);
        });


        helicopterListBut.setOnAction(event -> {
            application.setStage(7);
        });

    }

}
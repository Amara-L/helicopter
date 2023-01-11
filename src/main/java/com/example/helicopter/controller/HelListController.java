package com.example.helicopter.controller;

import com.example.helicopter.Application;
import com.example.helicopter.service.HelicopterService;
import com.example.helicopter.entity.Helicopter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelListController {

    @Autowired
    private HelicopterService helicopterService;

    @FXML
    private TextArea heliList;
    @FXML
    private Button updateBut;

    @FXML
    private Button retback;

    @Autowired
    private Application application;

    @FXML
    void initialize() {

        retback.setOnAction(event -> {
            application.setStage(8);
        });

        updateBut.setOnAction(event -> {
            List<Helicopter> helicopters = helicopterService.getHelicopters();
            heliList.setText(helicopterService.createText(helicopters));
        });
    }

}
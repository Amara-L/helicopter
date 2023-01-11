package com.example.helicopter;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;

    @Autowired
    @Qualifier("mainView")
    private ConfigurationControllers.View mainView;

    @Autowired
    @Qualifier("helSearchView")
    private ConfigurationControllers.View helSearchView;

    @Autowired
    @Qualifier("helListView")
    private ConfigurationControllers.View helListView;

    @Autowired
    @Qualifier("helicopView")
    private ConfigurationControllers.View helicopView;

    @Autowired
    @Qualifier("catHelManView")
    private ConfigurationControllers.View catHelManView;

    @Autowired
    @Qualifier("catEnTypeView")
    private ConfigurationControllers.View catEnTypeView;

    @Autowired
    @Qualifier("catCountryView")
    private ConfigurationControllers.View catCountryView;

    @Autowired
    @Qualifier("caEnManView")
    private ConfigurationControllers.View caEnManView;

    @Autowired
    @Qualifier("addNoteView")
    private ConfigurationControllers.View addNoteView;

    @Autowired
    @Qualifier("updateHelicopterView")
    private ConfigurationControllers.View updateHelicopterView;

    @Autowired
    @Qualifier("deleteNoteView")
    private ConfigurationControllers.View deleteNoteView;

    private static Scene caEnManScene;
    private static Scene catHelManScene;
    private static Scene catEnTypeScene;
    private static Scene helicopScene;
    private static Scene catCountryScene;
    private static Scene helSearchScene;
    private static Scene helListScene;
    private static Scene mainScene;
    private static Scene addNoteScene;
    private static Scene deleteNoteScene;
    private static Scene editNoteScene;

    private static Stage stageMain;

    @Override
    public void start(Stage stage) throws Exception {
        stageMain = stage;
        initStage(stageMain);
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

    public void setStage(Integer i) {
        switch (i) {
            case 1:
                if (caEnManScene == null) caEnManScene = new Scene(caEnManView.getView());
                stageMain.setScene(caEnManScene);
                break;
            case 2:
                if (catHelManScene == null) catHelManScene = new Scene(catHelManView.getView());
                stageMain.setScene(catHelManScene);
                break;
            case 3:
                if (catEnTypeScene == null) catEnTypeScene = new Scene(catEnTypeView.getView());
                stageMain.setScene(catEnTypeScene);
                break;
            case 4:
                if (helicopScene == null) helicopScene = new Scene(helicopView.getView());
                stageMain.setScene(helicopScene);
                break;
            case 5:
                if (catCountryScene == null) catCountryScene = new Scene(catCountryView.getView());
                stageMain.setScene(catCountryScene);
                break;
            case 6:
                if (helSearchScene == null) helSearchScene = new Scene(helSearchView.getView());
                stageMain.setScene(helSearchScene);
                break;
            case 7:
                if (helListScene == null) helListScene = new Scene(helListView.getView());
                stageMain.setScene(helListScene);
                break;
            case 8:
                if (mainScene == null) mainScene = new Scene(mainView.getView());
                stageMain.setScene(mainScene);
                break;
            case 9:
                if (editNoteScene == null) editNoteScene = new Scene(updateHelicopterView.getView());
                stageMain.setScene(editNoteScene);
                break;
            case 10:
                if (addNoteScene == null) addNoteScene = new Scene(addNoteView.getView());
                stageMain.setScene(addNoteScene);
                break;
            case 11:
                if (deleteNoteScene == null) deleteNoteScene = new Scene(deleteNoteView.getView());
                stageMain.setScene(deleteNoteScene);
                break;
        }
    }

    private void initStage(Stage stage) throws IOException {
        stage.setTitle(windowTitle);
        mainScene = new Scene(mainView.getView());
        stage.setScene(mainScene);
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

}
package com.example.helicopter;

import com.example.helicopter.controller.CaEnManController;
import com.example.helicopter.controller.CatCountryController;
import com.example.helicopter.controller.CatEnTypeController;
import com.example.helicopter.controller.CatHelManController;
import com.example.helicopter.controller.HelListController;
import com.example.helicopter.controller.HelSearchController;
import com.example.helicopter.controller.HelicopController;
import com.example.helicopter.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ConfigurationControllers {

    @Bean(name = "mainView")
    public View getMainView() throws IOException {
        return loadView("main.fxml");
    }

    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainView().getController();
    }

    @Bean(name = "helSearchView")
    public View getHelSearchView() throws IOException {
        return loadView("helicopter_search.fxml");
    }

    @Bean
    public HelSearchController geHelSearchController() throws IOException {
        return (HelSearchController) getHelSearchView().getController();
    }

    @Bean(name = "helListView")
    public View getHelListView() throws IOException {
        return loadView("helicopter_list.fxml");
    }

    @Bean
    public HelListController geHelListController() throws IOException {
        return (HelListController) getHelListView().getController();
    }

    @Bean(name = "helicopView")
    public View getHelicopView() throws IOException {
        return loadView("helicopter.fxml");
    }

    @Bean
    public HelicopController geHelicopController() throws IOException {
        return (HelicopController) getHelicopView().getController();
    }

    @Bean(name = "catHelManView")
    public View getCatHelManView() throws IOException {
        return loadView("catalog_helicopter_manufacture.fxml");
    }

    @Bean
    public CatHelManController geCatHelManController() throws IOException {
        return (CatHelManController) getCatHelManView().getController();
    }

    @Bean(name = "catEnTypeView")
    public View getCatEnTypeView() throws IOException {
        return loadView("catalog_engine_type.fxml");
    }

    @Bean
    public CatEnTypeController geCatEnTypeController() throws IOException {
        return (CatEnTypeController) getCatEnTypeView().getController();
    }

    @Bean(name = "catCountryView")
    public View getCatCountryView() throws IOException {
        return loadView("catalog_country.fxml");
    }

    @Bean
    public CatCountryController geCatCountryController() throws IOException {
        return (CatCountryController) getCatCountryView().getController();
    }

    @Bean(name = "caEnManView")
    public View getCaEnManView() throws IOException {
        return loadView("catalog_engine_manufacture.fxml");
    }

    @Bean
    public CaEnManController geCaEnManController() throws IOException {
        return (CaEnManController) getCaEnManView().getController();
    }

    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    /**
     * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
     * а view - представление, нам предстоит использовать в точке входа {@link Application}.
     */
    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }

}
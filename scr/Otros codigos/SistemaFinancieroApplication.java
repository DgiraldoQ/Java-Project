package com.sistemafinanciero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SistemaFinancieroApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        // Inicializa el contexto Spring Boot antes de iniciar JavaFX
        springContext = new SpringApplicationBuilder(SistemaFinancieroApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista.fxml"));
        // Configura el controllerFactory para que Spring gestione los controladores
        loader.setControllerFactory(springContext::getBean);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Sistema Financiero");
        stage.show();
    }

    @Override
    public void stop() {
        // Cierra el contexto Spring cuando se cierra la app JavaFX
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("newrequest.fxml"));
        primaryStage.setTitle("Petición de Servicio");
        primaryStage.setScene(new Scene(root, 800, 430));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

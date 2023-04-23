package com.example.knn_gui;

import com.example.knn_logic.prepare_data.Article;
import com.example.knn_logic.prepare_data.Loader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        /////////////
        // Main
        /////////////
        Loader loader = new Loader("data/");
        List<Article> articles = loader.loadData();



        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("input-view.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        InputController controller = fxmlLoader.<InputController>getController();
        controller.getStart_button().setOnAction(event -> {controller.onButtonStart(articles);});
        Scene scene = new Scene(root, 600, 800);
        stage.setTitle("KNN Classification");
        stage.setScene(scene);




        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
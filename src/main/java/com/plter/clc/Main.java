package com.plter.clc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("代码行数统计工具");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 220);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

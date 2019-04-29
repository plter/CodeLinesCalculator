package com.plter.clc;

import com.plter.clc.views.ViewTool;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("代码行数统计工具");
        primaryStage.setScene(new Scene(ViewTool.INSTANCE.loadView("views/MainView.fxml"), 720, 220));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.plter.clc.controllers;

import com.plter.clc.reader.CalculateTool;
import com.plter.clc.reader.IReaderCallback;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class MainViewController {


    public TextField tfDirPath;
    public VBox rootContainer;
    public TextField tfFileExts;
    public Label labelCurrentFile;
    public Label labelTotalLine;
    private File currentSelectedDir;

    public void btnSelectDirClicked(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        File selectedDir = dc.showDialog(rootContainer.getScene().getWindow());
        if (selectedDir != null) {
            currentSelectedDir = selectedDir;
            tfDirPath.setText(selectedDir.getAbsolutePath());
        }
    }

    public void startCalculate(ActionEvent actionEvent) {
        String exts = tfFileExts.getText();
        if (exts == null || exts.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "请填写要包含计算的文件扩展名", ButtonType.OK).show();
            return;
        }

        if (currentSelectedDir == null) {
            new Alert(Alert.AlertType.WARNING, "请选择目录", ButtonType.OK).show();
            return;
        }

        if (CalculateTool.INSTANCE.isRunning()) {
            new Alert(Alert.AlertType.WARNING, "当前已有任务正在执行", ButtonType.OK).show();
            return;
        }

        CalculateTool.INSTANCE.startCalculate(currentSelectedDir, exts, new IReaderCallback() {

            @Override
            public void onComplete() {
                labelCurrentFile.setText("完成");
            }

            @Override
            public void onProgress(@NotNull File currentFile, int currentLines, int linesTotal) {
                labelCurrentFile.setText(currentFile.getAbsolutePath());
                labelTotalLine.setText("总行数：" + linesTotal);
            }
        });
    }
}

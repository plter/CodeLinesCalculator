package com.plter.clc.views

import java.io.IOException
import javafx.fxml.FXMLLoader
import javafx.scene.Parent


object ViewTool {

    fun loadView(viewName: String): Parent {
        return FXMLLoader.load(ClassLoader.getSystemClassLoader().getResource(viewName))
    }
}
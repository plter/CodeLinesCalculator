module com.plter.clc {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlinx.coroutines.core;


    opens com.plter.clc.controllers to javafx.fxml;
    exports com.plter.clc;
}